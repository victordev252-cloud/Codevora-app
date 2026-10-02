package com.example.clockapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun DynamicClockLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(60.dp)) {
        val radius = size.minDimension / 2
        val center = size / 2f

        // Gradation Background
        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFF38BDF8), Color(0xFF818CF8))
            ),
            radius = radius
        )

        // Inner Circle
        drawCircle(
            color = Color.White,
            radius = radius * 0.85f,
            style = Stroke(width = 4.dp.toPx())
        )

        // Clock Hands (Astaanta Saacadda)
        drawLine(
            color = Color.White,
            start = center,
            end = center.copy(y = center.y - radius * 0.5f),
            strokeWidth = 4.dp.toPx()
        )
        drawLine(
            color = Color.White,
            start = center,
            end = center.copy(x = center.x + radius * 0.35f),
            strokeWidth = 4.dp.toPx()
        )
    }
}
