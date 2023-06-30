package com.example.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.data.todos.Todo
import com.example.todoapp.data.todos.TodoDao
import com.example.todoapp.data.user_goals.SingleGoal
import com.example.todoapp.data.user_goals.SingleGoalTypeConvertor
import com.example.todoapp.data.user_goals.UserGoalsDao

@Database(
    entities = [Todo::class, SingleGoal::class],
    version = 6
)
@TypeConverters(SingleGoalTypeConvertor::class)
abstract class ToGoalistDatabase: RoomDatabase(){
    abstract val todoDao: TodoDao
    abstract val userGoalsDao: UserGoalsDao
}