package com.example.todoapp.ui.todo_item

import com.example.todoapp.data.todos.Todo
import com.example.todoapp.data.user_goals.SingleGoal

sealed class TodoItemEvent{
    data class OnMarkTodoDone(val todo: Todo, val goalsToUpdate: Map<SingleGoal, Int>): TodoItemEvent()
    data class OnDeleteTodo(val todo: Todo): TodoItemEvent()
}
