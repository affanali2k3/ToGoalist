package com.example.todoapp.ui.todo_list.views

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mynewapp.util.UiEvent
import com.example.todoapp.ui.add_edit_todo.views.widgets.AddTodoDialog
//import com.example.todoapp.ui.add_edit_todo.views.
import com.example.todoapp.ui.todo_list.TodoItem
import com.example.todoapp.ui.todo_list.TodoListEvent
import com.example.todoapp.ui.todo_list.TodoListViewModel
import com.example.todoapp.ui.todo_list.views.widgets.topGoalsWidget

@Composable
fun TodoListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit, viewModel: TodoListViewModel = hiltViewModel()
) {
    val todos = viewModel.todos.collectAsState(initial = emptyList())
    val goals = viewModel.goals.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.showSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message, actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TodoListEvent.OnUndoTodoClick)
                    }
                }

                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerBackgroundColor = Color.Red,
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(TodoListEvent.OnAddTodoClick) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 10.dp)
        ) {
            AddTodoDialog()
            topGoalsWidget(goals.value)
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                items(todos.value) { todo ->
                    TodoItem(todo = todo,
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable {
                                viewModel.onEvent(
                                    TodoListEvent.OnViewTodoClick(todo)
                                )
                            })
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }


}



