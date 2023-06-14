package com.example.todoapp.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.todos.Todo
import com.example.todoapp.data.todos.TodoRepository
import com.example.mynewapp.util.Routes
import com.example.mynewapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {
    val todos = repository.getTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var lastDeletedTodo: Todo? = null

    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.OnViewTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnUndoTodoClick -> {
                lastDeletedTodo?.let { todo->  viewModelScope.launch {
                     repository.insertTodo(todo)
                }}

            }
            is TodoListEvent.OnDoneTodoClick -> {
                viewModelScope.launch {
                    repository.deleteTodo(event.todo)
                }
            }
            is TodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    repository.deleteTodo(event.todo)
                }
                lastDeletedTodo = event.todo
                sendUiEvent(UiEvent.showSnackbar(
                    message = "Todo deleted",
                    action = "Undo"
                ))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}