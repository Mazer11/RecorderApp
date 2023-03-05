package com.internship.recorderapp.ui.screens.main.components

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun RecordCard(
    fileName: String,
    fileLengt: String,
    createDate: String,
    modifier: Modifier = Modifier,
    onPlay: () -> Unit = {},
    onPause: () -> Unit = {}
) {
    val isStarted = remember { mutableStateOf(false) }

    Card() {

    }

}