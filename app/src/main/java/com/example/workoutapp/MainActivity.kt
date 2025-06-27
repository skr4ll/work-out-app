package com.example.workoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.workoutapp.ui.WorkoutScreen
import com.example.workoutapp.ui.theme.WorkoutAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutAppTheme {
                WorkoutScreen()
            }
        }
    }
}
