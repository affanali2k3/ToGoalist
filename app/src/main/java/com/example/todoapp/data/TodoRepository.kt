package com.example.todoapp.data

interface TodoRepository {
    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun getTodo(id: Int): Todo?

    fun getTodos(): kotlinx.coroutines.flow.Flow<List<Todo>>
}