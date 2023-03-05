package com.internship.recorderapp.utils.audio.player

import android.media.MediaPlayer
import androidx.core.net.toUri
import com.internship.recorderapp.RecorderApplication
import java.io.File
import javax.inject.Inject

class VoicePlayer @Inject constructor(
    private val application: RecorderApplication
): AudioPlayer {

    private var player:MediaPlayer? = null

    override fun playAudioFile(file: File) {
        MediaPlayer.create(application, file.toUri()).apply {
            player = this
            start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}