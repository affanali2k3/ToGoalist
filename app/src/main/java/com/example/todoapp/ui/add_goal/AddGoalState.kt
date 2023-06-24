package com.example.todoapp.ui.add_goal

import com.maxkeppeker.sheets.core.models.base.UseCaseState
import java.time.LocalDate

data class AddGoalState(
    val title: String = "",
    val color: String = "Yellow",
    val maxPoints: String = "",
    val priority: String = "None",
    val deadline: LocalDate = LocalDate.now(),
    val showCalendar: Boolean = false,
    )
