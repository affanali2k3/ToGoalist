package com.example.todoapp.ui.todo_list.views.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.data.user_goals.SingleGoal
import com.example.todoapp.ui.todo_list.TodoListEvent
import com.example.todoapp.ui.todo_list.TodoListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun topGoalsWidget(userGoals: List<SingleGoal>, viewModel: TodoListViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier
            .padding(all = 10.dp)
            .height(120.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(4.dp),
                clip = true
            )
            .combinedClickable(onClick = {
                println("Click")
                viewModel.onEvent(TodoListEvent.OnAddGoalClick)
            }, onLongClick = {
                println("Long Click")
                viewModel.onEvent(TodoListEvent.OnDeleteAllGoalsLongClick)
            }),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Icon(
                    modifier = Modifier.size(100.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar"
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                items(userGoals) {
                    progressIndicator(color = Color.Blue, progress = 0.7f)
                }
            }
        }
    }
}