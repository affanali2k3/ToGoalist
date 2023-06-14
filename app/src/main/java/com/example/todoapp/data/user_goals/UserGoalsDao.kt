package com.example.todoapp.data.user_goals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserGoalsDao {
    @Insert
    suspend fun insertUserGoal(goal: SingleGoal)

    @Query(value = "SELECT * FROM userGoals")
    fun getUserGoals(): Flow<List<SingleGoal>>
}