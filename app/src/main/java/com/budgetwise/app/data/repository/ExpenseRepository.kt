package com.budgetwise.app.data.repository

import androidx.lifecycle.LiveData
import com.budgetwise.app.data.db.CategoryTotal
import com.budgetwise.app.data.db.ExpenseDao
import com.budgetwise.app.data.entities.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    suspend fun addExpense(expense: Expense): Long =
        expenseDao.insertExpense(expense)

    suspend fun updateExpense(expense: Expense) =
        expenseDao.updateExpense(expense)

    suspend fun deleteExpense(expense: Expense) =
        expenseDao.deleteExpense(expense)

    fun getExpensesForPeriod(userId: Int, startDate: String, endDate: String): LiveData<List<Expense>> =
        expenseDao.getExpensesForPeriod(userId, startDate, endDate)

    fun getTotalPerCategory(userId: Int, startDate: String, endDate: String): LiveData<List<CategoryTotal>> =
        expenseDao.getTotalPerCategory(userId, startDate, endDate)

    fun getTotalSpentForPeriod(userId: Int, startDate: String, endDate: String): LiveData<Double> =
        expenseDao.getTotalSpentForPeriod(userId, startDate, endDate)

    suspend fun getExpenseById(id: Int): Expense? =
        expenseDao.getExpenseById(id)
}
