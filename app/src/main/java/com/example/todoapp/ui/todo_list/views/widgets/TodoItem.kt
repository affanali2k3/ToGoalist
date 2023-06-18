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

@Composable
fun TodoItem(todo: Todo, onEvent: (TodoListEvent) -> Unit, modifier: Modifier) {
    Surface(
        modifier = Modifier.shadow(
            elevation = 4.dp, shape = RoundedCornerShape(4.dp), clip = true
        )
    ) {
        Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Blue, shape = CircleShape)
                            .size(10.dp)
                    ) {}
                    Spacer(modifier = Modifier.width(width = 4.dp))
                    Text(text = "+2")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = todo.title, fontSize = 17.sp, fontWeight = FontWeight.Normal)
                    RoundedCheckView(todo, onEvent)
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
fun RoundedCheckView(todo: Todo, onEvent: (TodoListEvent) -> Unit) {
    val isChecked = remember { mutableStateOf(false) }
    val circleSize = remember { mutableStateOf(20.dp) }
    val circleThickness = remember { mutableStateOf(2.dp) }
    val color = remember { mutableStateOf(Color.Gray) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .toggleable(value = isChecked.value, role = Role.Checkbox) {
                isChecked.value = it
                onEvent(
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