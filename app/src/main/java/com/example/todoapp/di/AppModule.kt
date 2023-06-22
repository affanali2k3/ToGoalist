package com.example.todoapp.di

import android.app.Application
import androidx.room.Room
import com.example.todoapp.data.ToGoalistDatabase
import com.example.todoapp.data.todos.TodoRepository
import com.example.todoapp.data.user_goals.UserGoalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): ToGoalistDatabase {
        return Room.databaseBuilder(app, ToGoalistDatabase::class.java, "to_goal_list_db").fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: ToGoalistDatabase): TodoRepository {
        return TodoRepository(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideUserGoalsRepository(db: ToGoalistDatabase): UserGoalsRepository {
        return UserGoalsRepository(db.userGoalsDao)
    }
}