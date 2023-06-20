package com.example.todoapp.ui.todo_list.views.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.ui.add_edit_todo.AddEditTodoEvent
import com.example.todoapp.ui.add_edit_todo.AddEditTodoViewModel
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddTodo(
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val categoryPoints = remember { mutableStateOf("") }
    val hours = remember { mutableStateOf(LocalTime.now().hour) }
    val minutes = remember { mutableStateOf(LocalTime.now().minute) }
    val date = remember { mutableStateOf(LocalDate.now()) }
    val showCalendar = remember { mutableStateOf(false) }
    val calendarState = UseCaseState(
        onFinishedRequest = {
            showCalendar.value = false
        }
    )
    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date {
            date.value = it
        },
    )
    val showClock = remember { mutableStateOf(false) }
    val clockState = UseCaseState(
        onFinishedRequest = {
            showClock.value = false
        }
    )
    Column {
        OutlinedTextField(
            value = state.value.title,
            onValueChange = { viewModel.onEvent(AddEditTodoEvent.OnTitleChange(it))},
            placeholder = { Text(text = "Title") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.value.description,
            onValueChange = { viewModel.onEvent(AddEditTodoEvent.OnDescriptionChange(it))},
            placeholder = { Text(text = "Description") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        SelectGoalDropDown(label = "Category", listItems = arrayOf("Weight", "Kotlin", "Saas"))
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Box(Modifier.width(80.dp)) {
                OutlinedTextField(
                    value = categoryPoints.value,
                    onValueChange = { newValue ->
                        categoryPoints.value = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    placeholder = { Text(text = "Points") }
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add goal")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        FlowRow() {
            goalAssignedToTaskDisplay("Kotlin", 2, Color.Yellow)
        }
        Spacer(modifier = Modifier.height(8.dp))
        SelectGoalDropDown(label = "Priority", listItems = arrayOf("High", "Medium", "Low", "None"))
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        showCalendar.value = true
                    }
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showCalendar.value) calendarState.show() else calendarState.hide()
            Text(text = "Date")
            Text(text = date.value.toString())
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        showClock.value = true
                    }
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showClock.value) clockState.show() else clockState.hide()

            Text(text = "Time")
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "${hours.value}: ${minutes.value}")
        }
    }
}

@Composable
fun goalAssignedToTaskDisplay(name: String, points: Int, color: Color){
    Box(
        Modifier
            .border(1.dp, Color.Black, CircleShape)
            .padding(4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = color)
                    .width(8.dp)
                    .height(8.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "$name +${points}")
        }
    }
}