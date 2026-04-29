package com.budgetwise.app.data.repository

import androidx.lifecycle.LiveData
import com.budgetwise.app.data.db.GoalDao
import com.budgetwise.app.data.entities.Goal

class GoalRepository(private val goalDao: GoalDao) {

    fun getGoalForMonth(userId: Int, month: String): LiveData<Goal?> =
        goalDao.getGoalForMonth(userId, month)

    suspend fun saveGoal(goal: Goal) {
        val existing = goalDao.getGoalForMonthSnapshot(goal.userId, goal.month)
        if (existing == null) {
            goalDao.insertGoal(goal)
        } else {
            goalDao.updateGoal(goal.copy(id = existing.id))
        }
    }
}
