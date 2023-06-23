package com.example.todoapp.data.todos

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

object TodoTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMap(map: Map<String, Int>): String {
        return gson.toJson(map)
    }

    @TypeConverter
    fun toMap(json: String): Map<String, Int>{
        val type = object : TypeToken<Map<String, Int>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromDate(date: LocalDate): String {
        return date.toString()
    }

    @TypeConverter
    fun toDate(json: String): LocalDate {
        return LocalDate.parse(json)
    }

    @TypeConverter
    fun fromTime(time: Pair<Int,Int>): String {
        return "${time.first},${time.second}"
    }

    @TypeConverter
    fun toTime(value: String): Pair<Int, Int> {
        val (first, second) = value.split(",").map { it.toInt() }
        return Pair(first, second)
    }
}