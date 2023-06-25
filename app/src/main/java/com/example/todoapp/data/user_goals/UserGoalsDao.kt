package com.example.todoapp.data.user_goals

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserGoalsDao {
    @Insert
    suspend fun insertUserGoal(goal: SingleGoal)

    @Query(value = "UPDATE userGoals SET currPoints = :currPoints WHERE title IN :goalName")
    suspend fun incrementGoalPoints(goalName: Set<String>, currPoints: Int)

    @Query(value = "SELECT * FROM userGoals")
    fun getUserGoals(): Flow<List<SingleGoal>>
}