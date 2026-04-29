package com.budgetwise.app.data.repository

import androidx.lifecycle.LiveData
import com.budgetwise.app.data.db.CategoryDao
import com.budgetwise.app.data.db.ExpenseDao
import com.budgetwise.app.data.entities.Category

class CategoryRepository(
    private val categoryDao: CategoryDao,
    private val expenseDao: ExpenseDao
) {
    fun getCategoriesForUser(userId: Int): LiveData<List<Category>> =
        categoryDao.getCategoriesForUser(userId)

    suspend fun getCategoriesSnapshot(userId: Int): List<Category> =
        categoryDao.getCategoriesSnapshot(userId)

    suspend fun addCategory(category: Category): Long =
        categoryDao.insertCategory(category)

    suspend fun updateCategory(category: Category) =
        categoryDao.updateCategory(category)

    suspend fun deleteCategory(category: Category) =
        categoryDao.deleteCategory(category)

    suspend fun getCategoryById(id: Int): Category? =
        categoryDao.getCategoryById(id)
}
