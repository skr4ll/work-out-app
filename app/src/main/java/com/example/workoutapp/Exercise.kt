package com.example.workoutapp
// Einzelne Übung
data class Exercise(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: Int,
    val durationSeconds: Int
)
