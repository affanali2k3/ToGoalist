package com.example.todoapp.data.todos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query(value = "SELECT * FROM todo WHERE id = :id")
    suspend fun getTodo(id: Int): Todo?

    @Query(value = "SELECT * FROM todo")
    fun getTodos(): kotlinx.coroutines.flow.Flow<List<Todo>>
}