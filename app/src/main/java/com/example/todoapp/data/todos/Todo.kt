package com.example.todoapp.data.todos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity
@TypeConverters(TodoTypeConverter::class)
data class Todo(
    val title: String,
    val description: String?,

    val categoriesWithPoints: Map<String, Int>,

    val priority: String,
    val date: LocalDate,
    val time: Pair<Int,Int>,
    val isDone: Boolean,
    @PrimaryKey val  id: Int? = null,
)