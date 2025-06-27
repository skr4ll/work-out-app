package com.example.workoutapp

import android.content.Context
import android.media.MediaPlayer

object SoundPlayer {
    fun play(context: Context, resId: Int) {
        val mp = MediaPlayer.create(context, resId)
        mp.setOnCompletionListener { it.release() }
        mp.start()
    }
}
