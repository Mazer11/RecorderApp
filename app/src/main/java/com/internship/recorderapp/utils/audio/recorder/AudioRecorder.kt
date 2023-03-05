package com.internship.recorderapp.utils.audio.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}