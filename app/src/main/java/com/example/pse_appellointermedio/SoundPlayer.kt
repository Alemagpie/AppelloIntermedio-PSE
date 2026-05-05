package com.example.pse_appellointermedio

import android.media.SoundPool
import android.media.AudioManager
import android.media.AudioAttributes

class SoundPlayer(context: android.content.Context) {
    private val sp: SoundPool = SoundPool.Builder()
        .setMaxStreams(6)
        .setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        )
        .build()

    private val soundIds = listOf(
        sp.load(context, R.raw.sound_r, 1),
        sp.load(context, R.raw.sound_g, 1),
        sp.load(context, R.raw.sound_b, 1),
        sp.load(context, R.raw.sound_m, 1),
        sp.load(context, R.raw.sound_y, 1),
        sp.load(context, R.raw.sound_c, 1)
    )

    fun playSound(index: Int) {
        sp.play(soundIds[index], 1f, 1f, 1, 0, 1f)
    }

    fun release() {
        sp.release()
    }
}