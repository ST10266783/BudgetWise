package com.budgetwise.app.ui.goals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetwise.app.data.entities.Goal
import com.budgetwise.app.data.repository.GoalRepository
import com.budgetwise.app.utils.DateUtils
import kotlinx.coroutines.launch

class GoalViewModel(
    private val repository: GoalRepository,
    private val userId: Int
) : ViewModel() {

    val operationResult = MutableLiveData<String>()

    val currentGoal: LiveData<Goal?> =
        repository.getGoalForMonth(userId, DateUtils.currentMonth())

    fun saveGoal(minStr: String, maxStr: String) {
        val min = minStr.toDoubleOrNull()
        val max = maxStr.toDoubleOrNull()
        when {
            min == null || max == null ->
                operationResult.value = "Please enter valid numbers."
            min < 0 || max < 0 ->
                operationResult.value = "Goals cannot be negative."
            max == 0.0 ->
                operationResult.value = "Maximum goal must be greater than 0."
            min > max ->
                operationResult.value = "Minimum goal cannot exceed maximum goal."
            else -> viewModelScope.launch {
                repository.saveGoal(
                    Goal(
                        userId = userId,
                        month = DateUtils.currentMonth(),
                        minimumGoal = min,
                        maximumGoal = max
                    )
                )
                operationResult.postValue("Goals saved for ${DateUtils.currentMonth()}!")
            }
        }
    }
}
