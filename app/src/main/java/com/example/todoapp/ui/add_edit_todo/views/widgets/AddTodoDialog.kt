package com.example.todoapp.ui.add_edit_todo.views.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.add_edit_todo.AddEditTodoEvent
import com.example.todoapp.ui.add_edit_todo.AddEditTodoViewModel
import com.example.todoapp.ui.add_goal.AddGoalEvent
import com.example.todoapp.ui.add_goal.AddGoalViewModel
import com.example.todoapp.ui.todo_list.TodoListViewModel
import com.example.todoapp.ui.todo_list.views.widgets.AddGoal
import com.example.todoapp.ui.todo_list.views.widgets.AddTodo

@Composable
fun AddTodoDialog(
    onDismissRequest: () -> Unit
) {
    val viewModel: AddEditTodoViewModel = hiltViewModel()
    val viewModelGoal: AddGoalViewModel = hiltViewModel()
    val todoGoal = mutableStateOf(true)


    AlertDialog(
        modifier = Modifier.height(600.dp),
        onDismissRequest = onDismissRequest,
        text = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ElevatedButton(onClick = {
                        todoGoal.value = true
                    }) {
                        Text("Todo")
                    }
                    ElevatedButton(onClick = {
                        todoGoal.value = false
                    }) {
                        Text("Goal")
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (todoGoal.value) AddTodo() else AddGoal()
            }
        }, buttons = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ElevatedButton(
                    onClick = onDismissRequest
                ) {
                    Text("Cancel")
                }
                ElevatedButton(onClick = {
                    if (todoGoal.value)
                        viewModel.onEvent(AddEditTodoEvent.OnSaveTodo)
                    else
                        viewModelGoal.onEvent(AddGoalEvent.OnSaveGoal)

                    onDismissRequest()
                }) {
                    Text("Save")
                }
            }
        })

}




