package com.budgetwise.app.ui.expenses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetwise.app.data.entities.Expense
import com.budgetwise.app.data.repository.ExpenseRepository
import com.budgetwise.app.utils.DateUtils
import com.budgetwise.app.utils.ImageUtils
import kotlinx.coroutines.launch

class ExpenseViewModel(
    private val repository: ExpenseRepository,
    private val userId: Int
) : ViewModel() {

    val operationResult = MutableLiveData<String>()

    private val _startDate = MutableLiveData(DateUtils.startOfCurrentMonth())
    private val _endDate   = MutableLiveData(DateUtils.endOfCurrentMonth())

    val startDate: LiveData<String> = _startDate
    val endDate: LiveData<String>   = _endDate

    private var _expensesSource: LiveData<List<Expense>>? = null
    private val _expenses = MediatorLiveData<List<Expense>>()
    val expenses: LiveData<List<Expense>> = _expenses

    init {
        refreshExpenses()
    }

    private fun refreshExpenses() {
        _expensesSource?.let { _expenses.removeSource(it) }
        val newSource = repository.getExpensesForPeriod(
            userId, _startDate.value!!, _endDate.value!!
        )
        _expensesSource = newSource
        _expenses.addSource(newSource) { _expenses.value = it }
    }

    fun setDateRange(start: String, end: String) {
        if (!DateUtils.isValidDate(start) || !DateUtils.isValidDate(end)) {
            operationResult.value = "Invalid date range."
            return
        }
        _startDate.value = start
        _endDate.value   = end
        refreshExpenses()
    }

    fun addExpense(
        categoryId: Int, amount: Double, date: String,
        startTime: String, endTime: String, description: String,
        receiptPath: String?
    ) {
        val error = validate(amount, date, startTime, endTime, description)
        if (error != null) { operationResult.value = error; return }

        viewModelScope.launch {
            repository.addExpense(
                Expense(
                    userId = userId,
                    categoryId = categoryId,
                    amount = amount,
                    date = date,
                    startTime = startTime,
                    endTime = endTime,
                    description = description.trim(),
                    receiptPath = receiptPath
                )
            )
            operationResult.postValue("Expense saved!")
        }
    }

    fun updateExpense(
        original: Expense, categoryId: Int, amount: Double, date: String,
        startTime: String, endTime: String, description: String, receiptPath: String?
    ) {
        val error = validate(amount, date, startTime, endTime, description)
        if (error != null) { operationResult.value = error; return }

        viewModelScope.launch {
            repository.updateExpense(
                original.copy(
                    categoryId = categoryId, amount = amount, date = date,
                    startTime = startTime, endTime = endTime,
                    description = description.trim(), receiptPath = receiptPath
                )
            )
            operationResult.postValue("Expense updated!")
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            ImageUtils.deleteImage(expense.receiptPath)
            repository.deleteExpense(expense)
            operationResult.postValue("Expense deleted.")
        }
    }

    private fun validate(
        amount: Double, date: String,
        startTime: String, endTime: String, description: String
    ): String? = when {
        amount <= 0                    -> "Amount must be greater than 0."
        !DateUtils.isValidDate(date)   -> "Invalid date. Use yyyy-MM-dd format."
        !DateUtils.isValidTime(startTime) -> "Invalid start time. Use HH:mm format."
        !DateUtils.isValidTime(endTime)   -> "Invalid end time. Use HH:mm format."
        description.isBlank()          -> "Description cannot be empty."
        else -> null
    }
}
