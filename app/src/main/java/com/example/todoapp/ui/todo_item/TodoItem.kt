package com.example.todoapp.ui.todo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.todoapp.data.todos.Todo
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.ui.add_goal.AddGoalEvent
import com.example.todoapp.ui.add_goal.AddGoalViewModel
import com.example.todoapp.ui.todo_item.TodoItemEvent
import com.example.todoapp.ui.todo_item.TodoItemViewModel
import com.example.todoapp.ui.todo_list.views.widgets.goalAssignedToTaskDisplay

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier,
) {
    Surface(
        modifier = Modifier.shadow(
            elevation = 4.dp, shape = RoundedCornerShape(4.dp), clip = true
        )
    ) {
        Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
            ) {
                FlowRow(verticalArrangement = Arrangement.Center) {
                    todo.categoriesWithPoints.forEach { (category, points) ->
                        goalAssignedToTaskDisplay(
                            name = category,
                            points = points,
                            color = Color.Yellow,
                            textSize = 10
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = todo.title, fontSize = 17.sp, fontWeight = FontWeight.Normal)
                    RoundedCheckView(todo)
                }
                todo.description?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = todo.description)
                }
            }
        }
    }
}

@Composable
fun RoundedCheckView(
    todo: Todo,
    viewModel: TodoItemViewModel = hiltViewModel()
) {
    val isChecked = remember { mutableStateOf(false) }
    val circleSize = remember { mutableStateOf(20.dp) }
    val circleThickness = remember { mutableStateOf(2.dp) }
    val color = remember { mutableStateOf(Color.Gray) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .toggleable(value = isChecked.value, role = Role.Checkbox) {
                isChecked.value = it
                viewModel.onEvent(TodoItemEvent.OnMarkTodoDone(todo = todo, goalNames = todo.categoriesWithPoints.keys, c ))
                onTodoListEvent(
                    TodoListEvent.OnDoneTodoClick(
                        todo, isChecked.value
                    )
                )

                if (isChecked.value) {
                    circleSize.value = 20.dp
                    circleThickness.value = 3.dp
                    color.value = Color.Black
                } else {
                    circleSize.value = 20.dp
                    circleThickness.value = 2.dp
                    color.value = Color.Gray
                }
            }) {
        Box(
            modifier = Modifier
                .size(circleSize.value)
                .background(color.value)
                .padding(circleThickness.value)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            if (isChecked.value) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "")
            }
        }
    }
}