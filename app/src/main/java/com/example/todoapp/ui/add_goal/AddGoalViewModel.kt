package com.example.todoapp.ui.add_goal

import com.example.todoapp.data.user_goals.UserGoalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddGoalViewModel @Inject constructor(
    private val repository: UserGoalsRepository
) {
    fun onEvent(event: AddGoalEvent) {
        when (event) {
            is AddGoalEvent.OnColorChange -> onColorChange(event.color)
            is AddGoalEvent.OnTitleChange -> onTitleChange(event.title)
            is AddGoalEvent.OnMaxPointsChanged -> onMaxPointsChange(event.maxPoints)
            is AddGoalEvent.OnDeadlineChanged -> onDeadlineChange(event.deadline)
            is AddGoalEvent.OnPriorityChanged -> onPriorityChange(event.priority)
            is AddGoalEvent.OnSaveGoal -> onSaveGoal()
        }
    }

    private fun onColorChange(newColor: Int) {}
    private fun onTitleChange(newTitle: String) {}
    private fun onMaxPointsChange(newMaxPoints: Int) {}
    private fun onDeadlineChange(newDeadline: LocalDate) {}
    private fun onPriorityChange(newPriority: String) {}
    private fun onSaveGoal() {}

}