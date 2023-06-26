package com.example.todoapp.data.todos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.todoapp.data.user_goals.SingleGoal
import java.time.LocalDate

@Entity
@TypeConverters(TodoTypeConverter::class)
data class Todo(
    val title: String,
    val description: String?,

    val categoriesWithPoints: Map<SingleGoal, Int>,

    val priority: String,
    val date: LocalDate,
    val time: Pair<Int,Int>,
    val isDone: Boolean,
    @PrimaryKey val  id: Int? = null,
)