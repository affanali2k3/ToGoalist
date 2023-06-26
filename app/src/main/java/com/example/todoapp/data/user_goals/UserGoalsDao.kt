package com.example.todoapp.data.user_goals

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserGoalsDao {
    @Insert
    suspend fun insertUserGoal(goal: SingleGoal)

    @Update
    suspend fun incrementGoalPoints(updatedGoals: List<SingleGoal>)

    @Query(value = "SELECT * FROM userGoals")
    fun getUserGoals(): Flow<List<SingleGoal>>
}