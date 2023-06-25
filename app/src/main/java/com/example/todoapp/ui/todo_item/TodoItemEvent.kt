package com.example.todoapp.ui.todo_item

import com.example.todoapp.data.todos.Todo

sealed class TodoItemEvent{
    data class OnMarkTodoDone(val todo: Todo, val goalNames: Set<String>, val currPoints: Int): TodoItemEvent()
    data class OnDeleteTodo(val todo: Todo): TodoItemEvent()
}
