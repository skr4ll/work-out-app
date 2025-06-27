package com.example.workoutapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workoutapp.WorkoutViewModel

@Composable
fun WorkoutScreen(viewModel: WorkoutViewModel = viewModel()) {
    val exercise by viewModel.currentExercise.observeAsState()
    val timeLeft by viewModel.timeLeft.observeAsState(0)
    val isPause by viewModel.isPause.observeAsState(false)
    val workoutFinished by viewModel.workoutFinished.observeAsState(false)
    val progress by viewModel.progress.observeAsState(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Workout Counter: $progress", style = MaterialTheme.typography.titleMedium)
        val currentExerciseNumber by viewModel.currentExerciseNumber.observeAsState(0)
        val totalExercises = viewModel.exerciseCount

        if (!workoutFinished && currentExerciseNumber > 0) {
            Text("Übung $currentExerciseNumber von $totalExercises", style = MaterialTheme.typography.titleMedium)
        }

        if (workoutFinished) {
            Text("Workout abgeschlossen!", style = MaterialTheme.typography.headlineMedium)
        } else if (exercise != null) {
            Image(painter = painterResource(id = exercise!!.imageResId), contentDescription = null)
            Text(exercise!!.name, style = MaterialTheme.typography.headlineSmall)
            Text(exercise!!.description, textAlign = TextAlign.Center)
        } else if (isPause) {
            Text("Pause...", style = MaterialTheme.typography.headlineMedium)
        }

        Text("Verbleibende Zeit: $timeLeft s", style = MaterialTheme.typography.titleLarge)

        if (!workoutFinished && timeLeft == 0 && exercise == null && !isPause || workoutFinished) {
            Button(
                onClick = { viewModel.startWorkout() }
            ) {
                Text("Workout starten")
            }
        }
    }
}
