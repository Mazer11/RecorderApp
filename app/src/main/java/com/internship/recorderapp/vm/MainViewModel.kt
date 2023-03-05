package com.internship.recorderapp.vm

import androidx.lifecycle.ViewModel
import com.internship.recorderapp.RecorderApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val application: RecorderApplication,
) : ViewModel() {


}