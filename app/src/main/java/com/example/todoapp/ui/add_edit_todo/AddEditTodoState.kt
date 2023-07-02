package com.example.todoapp.ui.add_edit_todo

import com.example.todoapp.data.user_goals.SingleGoal
import java.time.LocalDate
import java.time.LocalTime

data class AddEditTodoState(
    val title: String = "",
    val description: String = "",
    val currentCategory: SingleGoal = SingleGoal(
        title = "None",
        color = "None",
        maxPoints = 0,
        currPoints = 0,
        priority = "None",
        deadline = LocalDate.now()
    ),
    val currentPoints: String = "",
    val categoriesWithPoints: MutableMap<SingleGoal, Int> = mutableMapOf(),
    val priority: String = "None",
    val date: LocalDate = LocalDate.now(),
    val time: Pair<Int, Int> = Pair(LocalTime.now().hour, LocalTime.now().minute)
) {
}