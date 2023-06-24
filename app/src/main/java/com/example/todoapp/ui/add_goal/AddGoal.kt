package com.example.todoapp.ui.todo_list.views.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.ui.add_goal.AddGoalEvent
import com.example.todoapp.ui.add_goal.AddGoalViewModel
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGoal(viewModel: AddGoalViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsState()
    val calendarState = UseCaseState(
        onFinishedRequest = {
            viewModel.onEvent(AddGoalEvent.OnCloseCalendar)
        }
    )
    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date {
            viewModel.onEvent(AddGoalEvent.OnDeadlineChanged(it))
        },
    )

    Column {
        OutlinedTextField(
            value = state.value.title,
            onValueChange = {
                viewModel.onEvent(AddGoalEvent.OnTitleChange(it))
            },
            placeholder = { Text(text = "Title") }
        )
        Spacer(modifier = Modifier.height(8.dp))


        DropDownForColor(label = "Color", listItems = arrayOf("Yellow", "Green", "Purple", "Red"))
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = state.value.maxPoints,
            onValueChange = {
                viewModel.onEvent(AddGoalEvent.OnMaxPointsChanged(it))
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Max Points") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DropDownForPriority(label = "Priority", arrayOf("High", "Medium", "Low", "None"))
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = { viewModel.onEvent(AddGoalEvent.OnOpenCalendar) }
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (state.value.showCalendar) calendarState.show() else calendarState.hide()
            Text(text = "Deadline")
            Text(text = state.value.deadline.toString())
        }
    }
}