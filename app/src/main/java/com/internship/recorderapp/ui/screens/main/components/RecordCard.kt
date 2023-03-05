package com.internship.recorderapp.ui.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RecordCard(
    fileName: String,
    fileLengt: String,
    modifier: Modifier = Modifier,
    onPlay: () -> Boolean,
) {
    val isStarted = remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(text = fileName)
                Text(text = fileLengt)
            }
            IconButton(
                onClick = {
                    isStarted.value = onPlay()
                }
            ) {
                Icon(
                    imageVector = if (isStarted.value)
                        Icons.Default.Pause
                    else
                        Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
                )
            }
        }
    }

}