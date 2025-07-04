package com.example.workoutapp

import android.app.Application
import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private var exerciseIndex = 0
    private var timer: CountDownTimer? = null
    private val exercises = ExerciseData.exercises

    private val _currentExercise = MutableLiveData<Exercise?>()
    val currentExercise: LiveData<Exercise?> = _currentExercise

    private val _timeLeft = MutableLiveData(0)
    val timeLeft: LiveData<Int> = _timeLeft

    private val _isPause = MutableLiveData(false)
    val isPause: LiveData<Boolean> = _isPause

    private val _workoutFinished = MutableLiveData(false)
    val workoutFinished: LiveData<Boolean> = _workoutFinished

    private val _progress = MutableLiveData(getSavedProgress())
    val progress: LiveData<Int> = _progress

    private val _exerciseCount = exercises.size
    val exerciseCount: Int get() = _exerciseCount

    private val _currentExerciseNumber = MutableLiveData(0)
    val currentExerciseNumber: LiveData<Int> = _currentExerciseNumber



    fun startWorkout() {
        exerciseIndex = 0
        _currentExerciseNumber.value = 1
        _workoutFinished.value = false
        startExercise()
    }

    private fun startExercise() {
        _isPause.value = false
        _currentExerciseNumber.value = exerciseIndex + 1
        val exercise = exercises[exerciseIndex]
        _currentExercise.value = exercise
        startTimer(exercise.durationSeconds) {
            SoundPlayer.play(context, R.raw.end)
            // Pause nur starten wenn Übung nicht die letzte ist
            if (exerciseIndex < exercises.size -1 ){startPause()}
            else {finishWorkout()}
        }
        SoundPlayer.play(context, R.raw.start)
    }

    private fun startPause() {
        _isPause.value = true
        _currentExercise.value = null
        startTimer(2) {
            SoundPlayer.play(context, R.raw.pause_end)
            exerciseIndex++
            _currentExerciseNumber.value = exerciseIndex + 1
            if (exerciseIndex <= exercises.size - 1) {
                startExercise()
            }
            else {
                finishWorkout()
            }
        }
        SoundPlayer.play(context, R.raw.pause_start)
    }

    private fun startTimer(seconds: Int, onFinish: () -> Unit) {
        timer?.cancel()
        _timeLeft.value = seconds
        timer = object : CountDownTimer(seconds * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                onFinish()
            }
        }.start()
    }

    private fun finishWorkout() {
        _workoutFinished.value = true
        saveProgress()
        _currentExerciseNumber.value = 0
    }

    private fun saveProgress() {
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val done = prefs.getInt("completedWorkouts", 0) + 1
        prefs.edit().putInt("completedWorkouts", done).apply()
        _progress.value = done
    }

    private fun getSavedProgress(): Int {
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        return prefs.getInt("completedWorkouts", 0)
    }

    override fun onCleared() {
        timer?.cancel()
        super.onCleared()
    }
}
