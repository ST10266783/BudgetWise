package com.budgetwise.app.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.budgetwise.app.data.db.CategoryTotal
import com.budgetwise.app.data.entities.Category
import com.budgetwise.app.data.entities.Goal
import com.budgetwise.app.data.repository.CategoryRepository
import com.budgetwise.app.data.repository.ExpenseRepository
import com.budgetwise.app.data.repository.GoalRepository
import com.budgetwise.app.utils.DateUtils

class DashboardViewModel(
    private val expenseRepo: ExpenseRepository,
    private val goalRepo: GoalRepository,
    private val catRepo: CategoryRepository
) : ViewModel() {

    private val _userId = MutableLiveData<Int>()

    private val startDate = DateUtils.startOfCurrentMonth()
    private val endDate   = DateUtils.endOfCurrentMonth()
    private val month     = DateUtils.currentMonth()

    val totalSpent: LiveData<Double> = _userId.switchMap { uid ->
        expenseRepo.getTotalSpentForPeriod(uid, startDate, endDate)
    }

    val categoryTotals: LiveData<List<CategoryTotal>> = _userId.switchMap { uid ->
        expenseRepo.getTotalPerCategory(uid, startDate, endDate)
    }

    val categories: LiveData<List<Category>> = _userId.switchMap { uid ->
        catRepo.getCategoriesForUser(uid)
    }

    val goal: LiveData<Goal?> = _userId.switchMap { uid ->
        goalRepo.getGoalForMonth(uid, month)
    }

    fun init(userId: Int) {
        if (_userId.value != userId) _userId.value = userId
    }
}
