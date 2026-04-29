package com.budgetwise.app.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetwise.app.data.entities.Category
import com.budgetwise.app.data.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository,
    private val userId: Int
) : ViewModel() {

    val categories: LiveData<List<Category>> = repository.getCategoriesForUser(userId)
    val operationResult = MutableLiveData<String>()

    fun addCategory(name: String, colour: String, monthlyLimit: Double?) {
        if (name.isBlank()) {
            operationResult.value = "Category name cannot be empty."
            return
        }
        viewModelScope.launch {
            repository.addCategory(
                Category(
                    userId = userId,
                    name = name.trim(),
                    colour = colour,
                    monthlyLimit = monthlyLimit
                )
            )
            operationResult.postValue("Category \"${name.trim()}\" added!")
        }
    }

    fun updateCategory(category: Category, name: String, colour: String, monthlyLimit: Double?) {
        if (name.isBlank()) {
            operationResult.value = "Category name cannot be empty."
            return
        }
        viewModelScope.launch {
            repository.updateCategory(
                category.copy(name = name.trim(), colour = colour, monthlyLimit = monthlyLimit)
            )
            operationResult.postValue("Category updated!")
        }
    }

    fun deleteCategory(category: Category) {
        if (category.isDefault) {
            operationResult.value = "The default category cannot be deleted."
            return
        }
        viewModelScope.launch {
            repository.deleteCategory(category)
            operationResult.postValue("Category deleted. Its expenses moved to Uncategorized.")
        }
    }
}
