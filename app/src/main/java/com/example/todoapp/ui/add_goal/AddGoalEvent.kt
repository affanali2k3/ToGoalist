package com.example.todoapp.ui.add_goal

import com.example.todoapp.ui.add_edit_todo.AddEditTodoEvent
import java.time.LocalDate

sealed class AddGoalEvent{
    data class OnTitleChange(val title: String): AddGoalEvent()
    data class OnColorChange(val color: String): AddGoalEvent()
    data class OnMaxPointsChanged(val maxPoints: String): AddGoalEvent()
    data class OnPriorityChanged(val priority: String): AddGoalEvent()
    data class OnDeadlineChanged(val deadline: LocalDate): AddGoalEvent()
    object OnOpenCalendar: AddGoalEvent()
    object OnCloseCalendar: AddGoalEvent()
    object OnSaveGoal: AddGoalEvent()
}
