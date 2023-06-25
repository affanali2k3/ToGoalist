package com.example.todoapp.data.user_goals

import kotlinx.coroutines.flow.Flow

class UserGoalsRepository(
    private val dao: UserGoalsDao
) {
    fun getUserGoals(): Flow<List<SingleGoal>> {
        return dao.getUserGoals()
    }

    suspend fun incrementGoalPoints(goalName: Set<String>, currPoints: Int) {
        dao.incrementGoalPoints(goalName, currPoints)
    }

    suspend fun insertUserGoal(goal: SingleGoal) {
        dao.insertUserGoal(goal)
    }
}