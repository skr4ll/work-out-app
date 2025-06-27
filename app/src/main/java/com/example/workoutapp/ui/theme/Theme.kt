package com.example.workoutapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun WorkoutAppTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = androidx.compose.ui.graphics.Color(0xFF6200EE),
        onPrimary = androidx.compose.ui.graphics.Color.White,
        background = androidx.compose.ui.graphics.Color(0xFFF5F5F5),
        onBackground = androidx.compose.ui.graphics.Color.Black
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
