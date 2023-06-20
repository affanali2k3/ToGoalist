package com.example.todoapp.ui.todo_list.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.todo_list.TodoListViewModel
import com.example.todoapp.ui.todo_list.views.widgets.AddGoal
import com.example.todoapp.ui.todo_list.views.widgets.AddTodo
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoDialog(
) {
    val viewModel: TodoListViewModel = viewModel()
    val openDialog by viewModel.addTodoDialogOpen
    val todoGoal = mutableStateOf(true)


    if (openDialog) {
        AlertDialog(
            modifier = Modifier.height(600.dp),
            onDismissRequest = {
                viewModel.addTodoDialogOpen.value = false
            },
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
                    ElevatedButton(onClick = { /*TODO*/ }) {
                        Text("Cancel")
                    }
                    ElevatedButton(onClick = { /*TODO*/ }) {
                        Text("Save")
                    }
                }
            })
    }
}




