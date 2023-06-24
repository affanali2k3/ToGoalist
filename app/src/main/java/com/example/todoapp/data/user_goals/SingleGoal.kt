package com.example.todoapp.data.user_goals

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity(tableName = "userGoals")
@TypeConverters(SingleGoalTypeConvertor::class)
data class SingleGoal(
    val title: String,
    val color: String,
    val maxPoints: Int,
    val priority: String,
    val deadline: LocalDate,
    @PrimaryKey val id: Int? = null,
)
