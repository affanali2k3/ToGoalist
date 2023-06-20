package com.example.todoapp.ui.todo_list.views.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodo(){
    val title =remember { mutableStateOf("")}
    val description = remember {mutableStateOf("")}
    val categoryPoints =remember { mutableStateOf("")}

    val hours = remember {mutableStateOf(LocalTime.now().hour)}
    val minutes =remember { mutableStateOf(LocalTime.now().minute)}
    val date = remember {mutableStateOf(LocalDate.now())}

    val showCalendar = remember { mutableStateOf(false)}
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
    val showClock =remember { mutableStateOf(false)}
    val clockState = UseCaseState(
        onFinishedRequest = {
            showClock.value = false
        }
    )
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