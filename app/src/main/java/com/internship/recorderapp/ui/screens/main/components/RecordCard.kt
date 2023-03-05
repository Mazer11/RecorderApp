package com.internship.recorderapp.ui.screens.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordCard(
    fileName: String,
    showProgress: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Card(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = fileName)
            AnimatedVisibility(visible = showProgress) {
                CircularProgressIndicator(modifier = Modifier.size(16.dp))
            }
        }
    }
}