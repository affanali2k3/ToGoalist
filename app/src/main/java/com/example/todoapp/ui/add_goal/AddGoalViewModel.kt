package com.example.todoapp.ui.add_goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.user_goals.SingleGoal
import com.example.todoapp.data.user_goals.UserGoalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddGoalViewModel @Inject constructor(
    private val repository: UserGoalsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddGoalState())
    val uiState = _uiState.asStateFlow()
    fun onEvent(event: AddGoalEvent) {
        when (event) {
            is AddGoalEvent.OnColorChange -> onColorChange(event.color)
            is AddGoalEvent.OnTitleChange -> onTitleChange(event.title)
            is AddGoalEvent.OnMaxPointsChanged -> onMaxPointsChange(event.maxPoints)
            is AddGoalEvent.OnDeadlineChanged -> onDeadlineChange(event.deadline)
            is AddGoalEvent.OnPriorityChanged -> onPriorityChange(event.priority)
            is AddGoalEvent.OnOpenCalendar -> onOpenCalendar()
            is AddGoalEvent.OnCloseCalendar -> onCloseCalendar()
            is AddGoalEvent.OnSaveGoal -> onSaveGoal()
        }
    }

    private fun onColorChange(newColor: String) =
        _uiState.update { uiState.value.copy(color = newColor) }

    private fun onTitleChange(newTitle: String) =
        _uiState.update { uiState.value.copy(title = newTitle) }

    private fun onMaxPointsChange(newMaxPoints: String) =
        _uiState.update { uiState.value.copy(maxPoints = newMaxPoints) }

    private fun onDeadlineChange(newDeadline: LocalDate) =
        _uiState.update { uiState.value.copy(deadline = newDeadline) }

    private fun onPriorityChange(newPriority: String) =
        _uiState.update { uiState.value.copy(priority = newPriority) }

    private fun onOpenCalendar() =
        _uiState.update { uiState.value.copy(showCalendar = true) }

    private fun onCloseCalendar() =
        _uiState.update { uiState.value.copy(showCalendar = false) }


    private fun onSaveGoal() =
        viewModelScope.launch {
            repository.insertUserGoal(
                SingleGoal(
                    title = uiState.value.title,
                    color = uiState.value.color,
                    maxPoints = uiState.value.maxPoints.toInt(),
                    priority = uiState.value.priority,
                    deadline = uiState.value.deadline,
                    currPoints = 0
                )
            )
        }

}

