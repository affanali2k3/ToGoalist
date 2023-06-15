package com.example.todoapp.ui.todo_list

import android.widget.ProgressBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mynewapp.util.UiEvent
import com.example.todoapp.data.user_goals.SingleGoal

@Composable
fun TodoListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val todos = viewModel.todos.collectAsState(initial = emptyList())
    val goals = viewModel.goals.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.showSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TodoListEvent.OnUndoTodoClick)
                    }
                }

                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState, drawerBackgroundColor = Color.Red,
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(TodoListEvent.OnAddTodoClick) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 10.dp)
        ) {
//            ModalDrawer(drawerContent = ){}
            AlertDialogSample()
            topGoalsWidget(goals.value)
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                items(todos.value) { todo ->
                    TodoItem(
                        todo = todo,
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable {
                                viewModel.onEvent(
                                    TodoListEvent.OnViewTodoClick(todo)
                                )
                            })
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }


}

enum class CustomDialogPosition {
    BOTTOM, TOP
}


@Composable
fun AlertDialogSample() {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(false) }

            Button(onClick = {
                openDialog.value = true
            }) {
                Text("Click me")
            }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                    },
                    text = {
                        TextField(value = "ss", onValueChange = {})
                    }, buttons = {})
            }
        }
    }
}

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
//
//                progressIndicator(color = Color.Cyan, progress = 0.2f)
//                progressIndicator(color = Color.Green, progress = 0.9f)
//                progressIndicator(color = Color.Red, progress = 1f)
//                progressIndicator(color = Color.Yellow, progress = 0.1f)
            }
        }
    }
}

@Composable
fun progressIndicator(color: Color, progress: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        LinearProgressIndicator(
            modifier = Modifier.width(150.dp),
            progress = progress,
            color = color,
            backgroundColor = Color.LightGray,
        )
        Spacer(modifier = Modifier.width(width = 10.dp))
        Text(text = "${(progress * 100).toInt()}%", fontSize = TextUnit(10f, TextUnitType.Sp))
    }
}
