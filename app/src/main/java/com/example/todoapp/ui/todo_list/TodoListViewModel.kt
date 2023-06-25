package com.example.todoapp.ui.todo_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.todos.Todo
import com.example.todoapp.data.todos.TodoRepository
import com.example.mynewapp.util.Routes
import com.example.mynewapp.util.UiEvent
import com.example.todoapp.data.user_goals.SingleGoal
import com.example.todoapp.data.user_goals.UserGoalsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository,
    private val goalsRepository: UserGoalsRepository
) : ViewModel() {
    val todos = repository.getTodos()
    val goals = goalsRepository.getUserGoals()
    val addTodoDialogOpen: MutableState<Boolean> =
        mutableStateOf(false)
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var lastDeletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnViewTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }

            is TodoListEvent.OnAddTodoClick -> {
                addTodoDialogOpen.value = true
//                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }

            is TodoListEvent.OnUndoTodoClick -> {
                lastDeletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }

            }

            is TodoListEvent.OnDoneTodoClick -> doneTodo(event.todo)

            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    repository.deleteTodo(event.todo)
                }
                lastDeletedTodo = event.todo
                sendUiEvent(
                    UiEvent.showSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    )
                )
            }
        }
    }

    private fun doneTodo(todo: Todo){
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}