package com.example.todoapp.ui.add_edit_todo

import java.time.LocalDate
import java.time.LocalTime

data class AddEditTodoState(
    val title: String = "",
    val description: String = "",
    val currentCategory: String = "Weight",
    val currentPoints: String = "",
    val categoriesWithPoints: Map<String, Int> = mapOf(),
    val priority: String = "None",
    val date: LocalDate = LocalDate.now(),
    val time: Pair<Int, Int> = Pair(LocalTime.now().hour, LocalTime.now().minute)
)