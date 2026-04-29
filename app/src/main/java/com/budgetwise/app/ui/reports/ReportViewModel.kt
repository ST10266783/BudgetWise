package com.budgetwise.app.ui.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.budgetwise.app.data.db.CategoryTotal
import com.budgetwise.app.data.entities.Category
import com.budgetwise.app.data.repository.CategoryRepository
import com.budgetwise.app.data.repository.ExpenseRepository
import com.budgetwise.app.utils.DateUtils

data class CategoryReport(
    val category: Category,
    val total: Double
)

class ReportViewModel(
    private val expenseRepo: ExpenseRepository,
    private val catRepo: CategoryRepository,
    private val userId: Int
) : ViewModel() {

    private val _startDate = MutableLiveData(DateUtils.startOfCurrentMonth())
    private val _endDate   = MutableLiveData(DateUtils.endOfCurrentMonth())

    val startDate: LiveData<String> = _startDate
    val endDate: LiveData<String>   = _endDate

    val allCategories: LiveData<List<Category>> = catRepo.getCategoriesForUser(userId)

    private var _totalsSource: LiveData<List<CategoryTotal>>? = null
    private val _categoryTotals = MediatorLiveData<List<CategoryTotal>>()
    val categoryTotals: LiveData<List<CategoryTotal>> = _categoryTotals

    init { refreshTotals() }

    private fun refreshTotals() {
        _totalsSource?.let { _categoryTotals.removeSource(it) }
        val src = expenseRepo.getTotalPerCategory(userId, _startDate.value!!, _endDate.value!!)
        _totalsSource = src
        _categoryTotals.addSource(src) { _categoryTotals.value = it }
    }

    fun setDateRange(start: String, end: String) {
        _startDate.value = start
        _endDate.value   = end
        refreshTotals()
    }
}
