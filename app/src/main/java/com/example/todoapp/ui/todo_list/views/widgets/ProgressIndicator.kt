package com.example.todoapp.ui.todo_list.views.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
fun progressIndicator(color: Color, progress: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        LinearProgressIndicator(
            modifier = Modifier.width(150.dp),
            progress = progress,
            color = color,
            backgroundColor = Color.LightGray,
        )
        Spacer(modifier = Modifier.width(width = 10.dp))
        Text(text = "${(progress * 100).toInt()}%", fontSize = TextUnit(10f, TextUnitType.Sp))
    }
}
