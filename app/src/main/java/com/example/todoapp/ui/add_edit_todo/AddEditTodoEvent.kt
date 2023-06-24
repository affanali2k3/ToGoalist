package com.example.todoapp.ui.add_edit_todo

import java.time.LocalDate
import java.time.LocalTime

sealed class AddEditTodoEvent {
    data class OnTitleChange(val title: String) : AddEditTodoEvent()
    data class OnDescriptionChange(val description: String) : AddEditTodoEvent()
    data class OnCategoryChange(val category: String) : AddEditTodoEvent()
    data class OnPointsChange(val points: String) : AddEditTodoEvent()
    data class OnPriorityChange(val priority: String) : AddEditTodoEvent()
    data class OnDateChange(val date: LocalDate) : AddEditTodoEvent()
    data class OnTimeChange(val time: Pair<Int, Int>) : AddEditTodoEvent()

    object OnAddCategoryWithPoints : AddEditTodoEvent()
    object OnSaveTodo : AddEditTodoEvent()
    object OnAddTodoClick : AddEditTodoEvent()
}