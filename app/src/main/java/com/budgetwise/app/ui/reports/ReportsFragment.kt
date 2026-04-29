package com.budgetwise.app.ui.reports

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.repository.CategoryRepository
import com.budgetwise.app.data.repository.ExpenseRepository
import com.budgetwise.app.databinding.FragmentReportsBinding
import com.budgetwise.app.utils.DateUtils
import com.budgetwise.app.utils.SessionManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.Calendar

class ReportsFragment : Fragment() {

    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReportViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db      = AppDatabase.getDatabase(requireContext())
                val session = SessionManager(requireContext())
                val catRepo = CategoryRepository(db.categoryDao(), db.expenseDao())
                val expRepo = ExpenseRepository(db.expenseDao())
                @Suppress("UNCHECKED_CAST")
                return ReportViewModel(expRepo, catRepo, session.getUserId()) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCategoryReport.layoutManager = LinearLayoutManager(requireContext())

        // Observe both live data sources together
        viewModel.allCategories.observe(viewLifecycleOwner) { cats ->
            viewModel.categoryTotals.observe(viewLifecycleOwner) { totals ->
                if (cats == null || totals == null) return@observe
                val catMap = cats.associateBy { it.id }

                val reports = totals.mapNotNull { ct ->
                    val cat = catMap[ct.categoryId] ?: return@mapNotNull null
                    CategoryReport(cat, ct.total)
                }.sortedByDescending { it.total }

                // Pie chart
                if (reports.isEmpty()) {
                    binding.pieChart.setNoDataText("No expenses in this period.")
                    binding.pieChart.invalidate()
                    binding.rvCategoryReport.adapter = null
                    return@observe
                }

                val entries = reports.map { PieEntry(it.total.toFloat(), it.category.name) }
                val colors  = reports.map {
                    try { Color.parseColor(it.category.colour) } catch (e: Exception) { Color.GRAY }
                }

                val dataSet = PieDataSet(entries, "").apply {
                    this.colors = colors
                    valueTextSize = 11f
                    valueTextColor = Color.WHITE
                    sliceSpace = 2f
                }

                binding.pieChart.apply {
                    data = PieData(dataSet)
                    description.isEnabled = false
                    isDrawHoleEnabled = true
                    holeRadius = 40f
                    setEntryLabelColor(Color.BLACK)
                    setEntryLabelTextSize(10f)
                    legend.isEnabled = false
                    animateY(600)
                    invalidate()
                }

                binding.rvCategoryReport.adapter = CategoryReportAdapter(reports)
            }
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

        // Custom date pickers
        binding.btnFrom.setOnClickListener { pickDate(isStart = true) }
        binding.btnTo.setOnClickListener   { pickDate(isStart = false) }

        updateDateLabels()
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
