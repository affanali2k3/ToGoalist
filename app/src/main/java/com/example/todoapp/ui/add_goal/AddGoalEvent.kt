package com.example.todoapp.ui.add_goal

import java.time.LocalDate

sealed class AddGoalEvent{
    data class OnTitleChange(val title: String): AddGoalEvent()
    data class OnColorChange(val color: Int): AddGoalEvent()
    data class OnMaxPointsChanged(val maxPoints: Int): AddGoalEvent()
    data class OnPriorityChanged(val priority: String): AddGoalEvent()
    data class OnDeadlineChanged(val deadline: LocalDate): AddGoalEvent()
    object OnSaveGoal: AddGoalEvent()
}
