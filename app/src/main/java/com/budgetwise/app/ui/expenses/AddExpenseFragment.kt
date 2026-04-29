package com.budgetwise.app.ui.expenses

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.entities.Category
import com.budgetwise.app.data.entities.Expense
import com.budgetwise.app.data.repository.ExpenseRepository
import com.budgetwise.app.databinding.FragmentAddExpenseBinding
import com.budgetwise.app.utils.DateUtils
import com.budgetwise.app.utils.ImageUtils
import com.budgetwise.app.utils.SessionManager
import kotlinx.coroutines.launch
import java.io.File
import java.util.Calendar

class AddExpenseFragment : Fragment() {

    private var _binding: FragmentAddExpenseBinding? = null
    private val binding get() = _binding!!

    private var categories   = listOf<Category>()
    private var selectedCatIndex = 0
    private var receiptPath: String? = null
    private var tempPhotoUri: Uri?   = null
    private var editingExpense: Expense? = null

    private val viewModel: ExpenseViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db     = AppDatabase.getDatabase(requireContext())
                val session = SessionManager(requireContext())
                val repo   = ExpenseRepository(db.expenseDao())
                @Suppress("UNCHECKED_CAST")
                return ExpenseViewModel(repo, session.getUserId()) as T
            }
        }
    }

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            tempPhotoUri?.let { uri ->
                val path = ImageUtils.saveCompressedImage(requireContext(), uri)
                if (path != null) {
                    receiptPath = path
                    showReceiptPreview(path)
                }
            }
        }
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val path = ImageUtils.saveCompressedImage(requireContext(), it)
            if (path != null) {
                receiptPath = path
                showReceiptPreview(path)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val session = SessionManager(requireContext())
        val db      = AppDatabase.getDatabase(requireContext())

        // Load categories then optionally load expense for editing
        lifecycleScope.launch {
            categories = db.categoryDao().getCategoriesSnapshot(session.getUserId())
            val names = categories.map { it.name }
            val spinAdapter = ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_item, names
            ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            binding.spinnerCategory.adapter = spinAdapter

            binding.spinnerCategory.onItemSelectedListener =
                object : android.widget.AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p: android.widget.AdapterView<*>?, v: View?, pos: Int, id: Long) {
                        selectedCatIndex = pos
                    }
                    override fun onNothingSelected(p: android.widget.AdapterView<*>?) {}
                }

            val expenseId = arguments?.getInt("expenseId", -1) ?: -1
            if (expenseId != -1) {
                val exp = db.expenseDao().getExpenseById(expenseId)
                exp?.let { populateForEdit(it) }
            } else {
                binding.etDate.setText(DateUtils.today())
                binding.etStartTime.setText("09:00")
                binding.etEndTime.setText("09:30")
            }
        }

        // Date picker
        binding.etDate.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(requireContext(), { _, y, m, d ->
                binding.etDate.setText("%04d-%02d-%02d".format(y, m + 1, d))
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Time pickers
        binding.etStartTime.setOnClickListener { showTimePicker(binding.etStartTime) }
        binding.etEndTime.setOnClickListener   { showTimePicker(binding.etEndTime) }

        // Receipt
        binding.btnCamera.setOnClickListener  { openCamera() }
        binding.btnGallery.setOnClickListener { galleryLauncher.launch("image/*") }
        binding.btnRemoveReceipt.setOnClickListener {
            receiptPath = null
            binding.ivReceiptPreview.visibility = View.GONE
            binding.btnRemoveReceipt.visibility = View.GONE
        }

        binding.btnSave.setOnClickListener { saveExpense() }

        binding.btnDelete.setOnClickListener {
            editingExpense?.let { exp ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete Expense")
                    .setMessage("Permanently delete this expense?")
                    .setPositiveButton("Delete") { _, _ -> viewModel.deleteExpense(exp) }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        }

        viewModel.operationResult.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            if (msg == "Expense saved!" || msg == "Expense updated!" || msg == "Expense deleted.") {
                findNavController().navigateUp()
            }
        }
    }

    private fun populateForEdit(expense: Expense) {
        editingExpense = expense
        binding.tvTitle.text = "Edit Expense"
        binding.btnDelete.visibility = View.VISIBLE
        binding.etAmount.setText(expense.amount.toString())
        binding.etDate.setText(expense.date)
        binding.etStartTime.setText(expense.startTime)
        binding.etEndTime.setText(expense.endTime)
        binding.etDescription.setText(expense.description)
        val idx = categories.indexOfFirst { it.id == expense.categoryId }
        if (idx >= 0) binding.spinnerCategory.setSelection(idx)
        expense.receiptPath?.let { path ->
            receiptPath = path
            showReceiptPreview(path)
        }
    }

    private fun saveExpense() {
        val amount    = binding.etAmount.text.toString().toDoubleOrNull() ?: 0.0
        val date      = binding.etDate.text.toString()
        val startTime = binding.etStartTime.text.toString()
        val endTime   = binding.etEndTime.text.toString()
        val desc      = binding.etDescription.text.toString()
        val catId     = if (categories.isNotEmpty()) categories[selectedCatIndex].id else 1

        if (editingExpense == null) {
            viewModel.addExpense(catId, amount, date, startTime, endTime, desc, receiptPath)
        } else {
            viewModel.updateExpense(
                editingExpense!!, catId, amount, date, startTime, endTime, desc, receiptPath
            )
        }
    }

    private fun showTimePicker(target: com.google.android.material.textfield.TextInputEditText) {
        val cal = Calendar.getInstance()
        TimePickerDialog(requireContext(), { _, hour, minute ->
            target.setText("%02d:%02d".format(hour, minute))
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    private fun openCamera() {
        val photoFile = File(requireContext().cacheDir, "receipt_${System.currentTimeMillis()}.jpg")
        tempPhotoUri  = FileProvider.getUriForFile(
            requireContext(), "${requireContext().packageName}.provider", photoFile
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, tempPhotoUri)
        }
        cameraLauncher.launch(intent)
    }

    private fun showReceiptPreview(path: String) {
        binding.ivReceiptPreview.visibility = View.VISIBLE
        binding.btnRemoveReceipt.visibility = View.VISIBLE
        Glide.with(this).load(File(path)).into(binding.ivReceiptPreview)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
