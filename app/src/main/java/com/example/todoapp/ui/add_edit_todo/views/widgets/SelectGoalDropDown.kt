package com.example.todoapp.ui.todo_list.views.widgets

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectGoalDropDown(label: String, listItems: Array<String>) {
//    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
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


