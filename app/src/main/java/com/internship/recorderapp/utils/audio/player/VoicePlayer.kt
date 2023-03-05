package com.internship.recorderapp.utils.audio.player

import android.media.MediaPlayer
import androidx.core.net.toUri
import com.internship.recorderapp.RecorderApplication
import java.io.File
import javax.inject.Inject

class VoicePlayer @Inject constructor(
    private val application: RecorderApplication
) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playAudioFile(file: File, onComplete: () -> Unit): Int? {
        MediaPlayer.create(application, file.toUri()).apply {
            this.setOnCompletionListener { onComplete() }
            player = this
            start()
        }
        return player?.duration
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}