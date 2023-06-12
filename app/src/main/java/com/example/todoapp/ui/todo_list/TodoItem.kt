package com.example.todoapp.ui.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.todoapp.data.Todo
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TodoItem(todo: Todo, onEvent: (TodoListEvent) -> Unit, modifier: Modifier) {
    Surface(
        modifier = Modifier.shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(4.dp),
            clip = true
        )
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = todo.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { onEvent(TodoListEvent.OnDeleteTodoClick(todo)) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")

                    }


                }
                todo.description?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = todo.description)

                }
            }

            Checkbox(checked = todo.isDone, onCheckedChange = { isChecked ->
                onEvent(
                    TodoListEvent.OnDoneTodoClick(
                        todo,
                        isChecked
                    )
                )
            })
        }
    }
}