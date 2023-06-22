package com.example.todoapp.ui.todo_list.views.widgets

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.ui.add_edit_todo.AddEditTodoEvent
import com.example.todoapp.ui.add_edit_todo.AddEditTodoViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownForCategory(label: String, listItems: Array<String>, viewModel: AddEditTodoViewModel = hiltViewModel()) {
//    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
    val contextForToast = LocalContext.current.applicationContext
    val state = viewModel.uiState.collectAsState()
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
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
            value = state.value.currentCategory,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
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
                    viewModel.onEvent(AddEditTodoEvent.OnCategoryChange(selectedOption))
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownForPriority(label: String, listItems: Array<String>, viewModel: AddEditTodoViewModel = hiltViewModel()) {
//    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
    val contextForToast = LocalContext.current.applicationContext
    val state = viewModel.uiState.collectAsState()
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
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
            value = state.value.priority,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
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
                    viewModel.onEvent(AddEditTodoEvent.OnPriorityChange(selectedOption))
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownForColor(label: String, listItems: Array<String>, viewModel: AddEditTodoViewModel = hiltViewModel()) {
//    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
    val contextForToast = LocalContext.current.applicationContext
    val state = viewModel.uiState.collectAsState()
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
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
            value = state.value.currentCategory,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
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
                    viewModel.onEvent(AddEditTodoEvent.OnCategoryChange(selectedOption))
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}


