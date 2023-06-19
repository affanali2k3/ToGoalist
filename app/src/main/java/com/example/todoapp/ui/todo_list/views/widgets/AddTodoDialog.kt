package com.example.todoapp.ui.todo_list.views

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.todo_list.TodoListViewModel
import com.example.todoapp.ui.todo_list.views.widgets.SelectGoalDropDown
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoDialog(
) {
    val viewModel: TodoListViewModel = viewModel()
    val title = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }
    val categoryPoints = remember {
        mutableStateOf("")
    }
    var showMenu = remember { mutableStateOf(false) }
    val openDialog by viewModel.addTodoDialogOpen
    val date = remember {
        mutableStateOf(LocalDate.now())
    }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.addTodoDialogOpen.value = false
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = title.value,
                        onValueChange = { newValue ->
                            title.value = newValue
                        },
                        placeholder = { Text(text = "Title") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = description.value,
                        onValueChange = { newValue ->
                            description.value = newValue
                        },
                        placeholder = { Text(text = "Description") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SelectGoalDropDown(label = "Category")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = categoryPoints.value,
                        onValueChange = { newValue ->
                            categoryPoints.value = newValue
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "Points") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SelectGoalDropDown(label = "Priority")
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.clickable(
                            onClick = {
                            }
                        )
                    ) {
                        Text(text = "Date")
                        Text(text = date.toString())
                    }
                }
            }, buttons = {})
    }

}


