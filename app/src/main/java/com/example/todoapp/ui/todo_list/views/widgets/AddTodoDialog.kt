package com.example.todoapp.ui.todo_list.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.todo_list.TodoListViewModel

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
    var showMenu = remember { mutableStateOf(false) }
    val openDialog by viewModel.addTodoDialogOpen


    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                viewModel.addTodoDialogOpen.value = false
            },
            text = {
                Column() {
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
                    Row() {
                        SelectGoalDropDown()
                        Box(modifier = Modifier.width(10.dp).background(color = Color.Red))
                    }
                }
            }, buttons = {})
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectGoalDropDown() {
    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
    val contextForToast = LocalContext.current.applicationContext
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }
    // remember the selected item
    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }
    // box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // text field
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // this is a column scope
            // all the items are added vertically
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}


