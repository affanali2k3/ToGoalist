package com.example.todoapp.data.user_goals

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userGoals")
data class SingleGoal(
    val totalPoints: Int,
    val currentPoints: Int,
    val color: Int,
    @PrimaryKey val id: Int? = null,
)
