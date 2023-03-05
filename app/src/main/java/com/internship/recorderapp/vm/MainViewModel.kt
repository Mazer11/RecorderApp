package com.internship.recorderapp.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.internship.recorderapp.RecorderApplication
import com.internship.recorderapp.ui.screens.main.states.MainScreenState
import com.internship.recorderapp.utils.audio.player.VoicePlayer
import com.internship.recorderapp.utils.audio.recorder.VoiceRecorder
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: RecorderApplication,
) : ViewModel() {

    /**Current file to load audio*/
    private var audioFile: File? = null

    /**Voice player object*/
    private val player by lazy {
        VoicePlayer(application)
    }

    /**Voice recorder object*/
    private val recorder by lazy {
        VoiceRecorder(application)
    }

    /**Object that represents current MainScreen state*/
    private val _screenState: MutableLiveData<MainScreenState> by lazy {
        MutableLiveData<MainScreenState>(
            MainScreenState(
                isRecording = false
            )
        )
    }
    val screenState: LiveData<MainScreenState> = _screenState

    /**Current records list*/
    private val _records: MutableLiveData<List<File>> by lazy {
        MutableLiveData<List<File>>(emptyList())
    }
    val records: LiveData<List<File>> = _records

    fun startOrStopRecording() {

        if (_screenState.value?.isRecording == true) {

            _screenState.value = _screenState.value?.copy(
                isRecording = _screenState.value?.isRecording?.not() ?: false
            )
            Log.e("StartStop", "isRecording is true (check: ${_screenState.value?.isRecording})")


            recorder.stop()
            _records.value = buildList {
                addAll(_records.value!!)
                add(audioFile!!)
            }
        } else {

            _screenState.value = _screenState.value?.copy(
                isRecording = _screenState.value?.isRecording?.not() ?: false
            )

            Log.e("StartStop", "isRecording is false (check: ${_screenState.value?.isRecording})")
            audioFile = File(application.cacheDir, "audio.mp3")
            recorder.start(outputFile = audioFile!!)
        }
    }

}

























