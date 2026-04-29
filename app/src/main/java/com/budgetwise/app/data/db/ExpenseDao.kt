package com.budgetwise.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.budgetwise.app.data.entities.Expense

data class CategoryTotal(
    val categoryId: Int,
    val total: Double
)

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense): Long

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query(
        "SELECT * FROM expenses WHERE userId = :userId " +
        "AND date BETWEEN :startDate AND :endDate ORDER BY date DESC, createdAt DESC"
    )
    fun getExpensesForPeriod(
        userId: Int,
        startDate: String,
        endDate: String
    ): LiveData<List<Expense>>

    @Query(
        "SELECT categoryId, SUM(amount) as total FROM expenses " +
        "WHERE userId = :userId AND date BETWEEN :startDate AND :endDate GROUP BY categoryId"
    )
    fun getTotalPerCategory(
        userId: Int,
        startDate: String,
        endDate: String
    ): LiveData<List<CategoryTotal>>

    @Query(
        "SELECT COALESCE(SUM(amount), 0.0) FROM expenses " +
        "WHERE userId = :userId AND date BETWEEN :startDate AND :endDate"
    )
    fun getTotalSpentForPeriod(
        userId: Int,
        startDate: String,
        endDate: String
    ): LiveData<Double>

    @Query("SELECT * FROM expenses WHERE id = :id LIMIT 1")
    suspend fun getExpenseById(id: Int): Expense?
}
