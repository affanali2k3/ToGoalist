package com.example.todoapp.data.user_goals

import androidx.room.TypeConverter
import java.time.LocalDate

object SingleGoalTypeConvertor {

    @TypeConverter
    fun fromDate(date: LocalDate): String {
        println("Date is $date")
        return date.toString()
    }

    @TypeConverter
    fun toDate(json: String): LocalDate {
        println("Date after is $json")
        return LocalDate.parse(json)
    }
}