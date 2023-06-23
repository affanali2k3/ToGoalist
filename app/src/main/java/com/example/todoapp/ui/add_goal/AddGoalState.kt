package com.example.todoapp.ui.add_goal

import java.time.LocalDate

data class AddGoalState(
    val title: String,
    val color: Int,
    val maxPoints: Int,
    val priority: String,
    val deadline: LocalDate
)
