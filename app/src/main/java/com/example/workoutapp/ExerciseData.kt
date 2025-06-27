package com.example.workoutapp

// Definiere die Übungen die vorhanden sind
// Letzt ziffer stellt die Dauer der Übimg dar. Zum angenehmeren Testen auf 2 Sekunden gestellt überall.
object ExerciseData {
    val exercises = listOf(
        Exercise(1, "Liegestütze", "Klassische Liegestütze", R.drawable.liegestuetze, 2),
        Exercise(2, "Kniebeugen", "Tiefe Kniebeugen", R.drawable.kniebeugen, 2),
        Exercise(3, "Plank", "Halteposition für den Core", R.drawable.plank, 2),
    )
}
