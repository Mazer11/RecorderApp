package com.internship.recorderapp.utils.audio.recorder

import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import com.internship.recorderapp.RecorderApplication
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class VoiceRecorder @Inject constructor(
    private val application: RecorderApplication
) : AudioRecorder {

    private var recorder: MediaRecorder? = null

    override fun start(outputFile: File) {
        createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outputFile).fd)

            prepare()
            start()

            recorder = this
        }
    }

    override fun stop() {
        if (recorder != null) {
            try {
                recorder?.stop()
            } catch (e: Exception){
                Log.e("VoiceRecorderException", "Exception is: ${e.message}")
            }
            recorder?.reset()
            recorder = null
        }
    }

    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(application)
        } else {
            MediaRecorder()
        }
    }
}