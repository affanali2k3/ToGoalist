package com.example.todoapp.data.todos

import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val dao: TodoDao
) {
    suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    suspend fun getTodo(id: Int): Todo? {
        return dao.getTodo(id)
    }

    fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}