package com.example.todoapp.ui.todo_list

import com.example.todoapp.data.Todo

sealed class TodoListEvent{
    data class OnDeleteTodoClick(val todo: Todo): TodoListEvent()
    data class OnDoneTodoClick(val todo: Todo, val isDone: Boolean): TodoListEvent()
    data class OnViewTodoClick(val todo: Todo): TodoListEvent()
    object OnUndoTodoClick: TodoListEvent()
    object OnAddTodoClick: TodoListEvent()
}
