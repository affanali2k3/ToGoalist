package com.example.todoapp.data.user_goals

import androidx.room.TypeConverter
import java.time.LocalDate

object SingleGoalTypeConvertor {
    @TypeConverter
    fun fromDate(date: LocalDate): String {
        return date.toString()
    }

    @TypeConverter
    fun toDate(json: String): LocalDate {
        return LocalDate.parse(json)
    }
}