package com.budgetwise.app.ui.expenses

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.budgetwise.app.R
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.entities.Expense
import com.budgetwise.app.data.repository.ExpenseRepository
import com.budgetwise.app.databinding.FragmentExpensesBinding
import com.budgetwise.app.utils.DateUtils
import com.budgetwise.app.utils.SessionManager
import java.util.Calendar

class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ExpenseAdapter
    private var allExpenses = listOf<Expense>()

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpensesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExpenseAdapter(
            onItemClick = { expense ->
                val bundle = Bundle().apply { putInt("expenseId", expense.id) }
                findNavController().navigate(R.id.action_expenses_to_addExpense, bundle)
            },
            onReceiptClick = { expense ->
                val bundle = Bundle().apply { putString("imagePath", expense.receiptPath) }
                findNavController().navigate(R.id.action_expenses_to_receiptViewer, bundle)
            }
        )

        binding.rvExpenses.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExpenses.adapter = adapter

        viewModel.expenses.observe(viewLifecycleOwner) { list ->
            allExpenses = list ?: emptyList()
            applySearch(binding.etSearch.text.toString())
        }

        viewModel.operationResult.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        // Period chips
        binding.chipMonth.setOnClickListener {
            viewModel.setDateRange(DateUtils.startOfCurrentMonth(), DateUtils.endOfCurrentMonth())
            updateDateLabels()
        }
        binding.chipWeek.setOnClickListener {
            viewModel.setDateRange(DateUtils.startOfWeek(), DateUtils.today())
            updateDateLabels()
        }

        // Custom date range
        binding.btnFrom.setOnClickListener { pickDate(isStart = true) }
        binding.btnTo.setOnClickListener   { pickDate(isStart = false) }

        // Search
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = applySearch(s.toString())
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.fabAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_expenses_to_addExpense)
        }

        updateDateLabels()
    }

    private fun applySearch(query: String) {
        val filtered = if (query.isBlank()) allExpenses
        else allExpenses.filter {
            it.description.contains(query, ignoreCase = true) ||
            it.amount.toString().contains(query)
        }
        adapter.submitList(filtered)
        binding.tvEmpty.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun pickDate(isStart: Boolean) {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, y, m, d ->
                val picked = "%04d-%02d-%02d".format(y, m + 1, d)
                val start  = if (isStart) picked else viewModel.startDate.value!!
                val end    = if (!isStart) picked else viewModel.endDate.value!!
                viewModel.setDateRange(start, end)
                updateDateLabels()
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDateLabels() {
        binding.btnFrom.text = "From: ${viewModel.startDate.value}"
        binding.btnTo.text   = "To: ${viewModel.endDate.value}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
