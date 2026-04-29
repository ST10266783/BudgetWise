package com.budgetwise.app.ui.categories

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.repository.CategoryRepository
import com.budgetwise.app.databinding.FragmentAddCategoryBinding
import com.budgetwise.app.utils.SessionManager
import kotlinx.coroutines.launch

class AddCategoryFragment : Fragment() {

    private var _binding: FragmentAddCategoryBinding? = null
    private val binding get() = _binding!!

    private var selectedColour = "#FF6200EE"
    private var editCategoryId = -1

    // Simple colour palette
    private val colours = listOf(
        "#F44336", "#E91E63", "#9C27B0", "#3F51B5",
        "#2196F3", "#03A9F4", "#00BCD4", "#009688",
        "#4CAF50", "#8BC34A", "#CDDC39", "#FFC107",
        "#FF9800", "#FF5722", "#795548", "#9E9E9E"
    )
    private var colourIndex = 4

    private val viewModel: CategoryViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db     = AppDatabase.getDatabase(requireContext())
                val session = SessionManager(requireContext())
                val repo   = CategoryRepository(db.categoryDao(), db.expenseDao())
                @Suppress("UNCHECKED_CAST")
                return CategoryViewModel(repo, session.getUserId()) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editCategoryId = arguments?.getInt("categoryId", -1) ?: -1
        selectedColour = colours[colourIndex]
        updateColourPreview()

        // Colour picker buttons
        binding.btnPrevColour.setOnClickListener {
            colourIndex = (colourIndex - 1 + colours.size) % colours.size
            selectedColour = colours[colourIndex]
            updateColourPreview()
        }
        binding.btnNextColour.setOnClickListener {
            colourIndex = (colourIndex + 1) % colours.size
            selectedColour = colours[colourIndex]
            updateColourPreview()
        }

        // Load existing data if editing
        if (editCategoryId != -1) {
            binding.tvTitle.text = "Edit Category"
            lifecycleScope.launch {
                val db  = AppDatabase.getDatabase(requireContext())
                val cat = db.categoryDao().getCategoryById(editCategoryId) ?: return@launch
                binding.etName.setText(cat.name)
                binding.etMonthlyLimit.setText(cat.monthlyLimit?.toString() ?: "")
                selectedColour = cat.colour
                colourIndex = colours.indexOfFirst { it.equals(cat.colour, ignoreCase = true) }
                    .takeIf { it >= 0 } ?: 0
                updateColourPreview()
            }
        }

        binding.btnSave.setOnClickListener {
            val name  = binding.etName.text.toString()
            val limit = binding.etMonthlyLimit.text.toString().toDoubleOrNull()

            if (editCategoryId == -1) {
                viewModel.addCategory(name, selectedColour, limit)
            } else {
                lifecycleScope.launch {
                    val db  = AppDatabase.getDatabase(requireContext())
                    val cat = db.categoryDao().getCategoryById(editCategoryId) ?: return@launch
                    viewModel.updateCategory(cat, name, selectedColour, limit)
                }
            }
        }

        viewModel.operationResult.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            if (!msg.contains("empty") && !msg.contains("cannot")) {
                findNavController().navigateUp()
            }
        }
    }

    private fun updateColourPreview() {
        try {
            binding.vColourPreview.setBackgroundColor(Color.parseColor(selectedColour))
            binding.tvColourHex.text = selectedColour
        } catch (e: Exception) { /* ignore bad hex */ }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
