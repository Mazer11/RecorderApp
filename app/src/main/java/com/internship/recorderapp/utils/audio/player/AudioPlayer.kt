package com.internship.recorderapp.utils.audio.player

import java.io.File

interface AudioPlayer {
    fun playAudioFile(file: File, onComplete: () -> Unit): Int?
    fun stop()
}