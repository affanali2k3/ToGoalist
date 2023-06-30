package com.example.todoapp.data.todos

import androidx.room.TypeConverter
import com.example.todoapp.data.user_goals.SingleGoal
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

object TodoTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMap(map: Map<SingleGoal, Int>): String {
        val serializedMap = mutableMapOf<String, Int>()
        map.forEach { (goal, progress) ->
            val date = goal.deadline.toString()
            val json = JsonParser.parseString(
                gson.toJson(goal)
            ).asJsonObject
            json.addProperty("deadline", date)
            serializedMap[gson.toJson(json)] = progress
        }
        return gson.toJson(serializedMap)
    }

    @TypeConverter
    fun toMap(json: String): Map<SingleGoal, Int> {
        println("Before type conversion $json")
        val serializedMapType = object : TypeToken<Map<String, Int>>() {}.type
        val serializedMap = gson.fromJson<Map<String, Int>>(json, serializedMapType)
        val deserializedMap = mutableMapOf<SingleGoal, Int>()
        serializedMap.forEach { (key, value) ->
            val temp = gson.fromJson(key, JsonObject::class.java)
            val goal = SingleGoal(
                title = temp.get("title").asString,
                color = temp.get("color").asString,
                maxPoints = temp.get("maxPoints").asInt,
                currPoints = temp.get("maxPoints").asInt,
                priority = temp.get("priority").asString,
                deadline = LocalDate.parse(temp.get("deadline").asString),
                id = temp.get("id").asInt
            )
            deserializedMap[goal] = value
        }
        return deserializedMap


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
    fun fromTime(time: Pair<Int, Int>): String {
        return "${time.first},${time.second}"
    }

    @TypeConverter
    fun toTime(value: String): Pair<Int, Int> {
        val (first, second) = value.split(",").map { it.toInt() }
        return Pair(first, second)
    }
}