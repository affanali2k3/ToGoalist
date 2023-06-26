package com.example.todoapp.ui.todo_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.todos.Todo
import com.example.todoapp.data.todos.TodoRepository
import com.example.todoapp.data.user_goals.SingleGoal
import com.example.todoapp.data.user_goals.UserGoalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoItemViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val goalsRepository: UserGoalsRepository
) : ViewModel() {
    fun onEvent(event: TodoItemEvent) = when (event) {
        is TodoItemEvent.OnMarkTodoDone -> markTodoDone(event.todo, event.goalsToUpdate)
        is TodoItemEvent.OnDeleteTodo -> deleteTodo(event.todo)
    }

    private fun markTodoDone(todo: Todo, goalsToUpdate: Map<SingleGoal, Int>) {
        val updatedGoals: MutableList<SingleGoal> = mutableListOf()
        goalsToUpdate.forEach { (goal, pointsToAdd) ->
            updatedGoals.add(goal.copy(currPoints = goal.currPoints + pointsToAdd))
        }
        viewModelScope.launch { goalsRepository.incrementGoalPoints(updatedGoals) }
//        viewModelScope.launch { todoRepository.deleteTodo(todo) }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch { todoRepository.deleteTodo(todo) }
    }

}