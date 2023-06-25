package com.example.todoapp.ui.todo_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.todos.Todo
import com.example.todoapp.data.todos.TodoRepository
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
        is TodoItemEvent.OnMarkTodoDone -> markTodoDone(event.todo, event.goalNames, event.currPoints)
        is TodoItemEvent.OnDeleteTodo -> deleteTodo(event.todo)
    }

    private fun markTodoDone(todo: Todo, goalName: Set<String>, currPoints: Int) {
        viewModelScope.launch { todoRepository.deleteTodo(todo) }
        viewModelScope.launch { goalsRepository.incrementGoalPoints(goalName, currPoints) }
    }

    private fun deleteTodo(todo: Todo) {
        viewModelScope.launch { todoRepository.deleteTodo(todo) }
    }

}