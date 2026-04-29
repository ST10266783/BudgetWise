package com.budgetwise.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.budgetwise.app.data.entities.Goal

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal): Long

    @Update
    suspend fun updateGoal(goal: Goal)

    @Query("SELECT * FROM goals WHERE userId = :userId AND month = :month LIMIT 1")
    fun getGoalForMonth(userId: Int, month: String): LiveData<Goal?>

    @Query("SELECT * FROM goals WHERE userId = :userId AND month = :month LIMIT 1")
    suspend fun getGoalForMonthSnapshot(userId: Int, month: String): Goal?
}
