package com.internship.recorderapp.ui.screens.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.internship.recorderapp.ui.screens.main.components.RecordCard
import com.internship.recorderapp.vm.MainViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(vm: MainViewModel) {

    val screenState = vm.screenState.observeAsState()
    val records = vm.records.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ваши записи",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp),
                    onClick = {
                        vm.startOrStopRecording()
                    }
                ) {
                    Icon(
                        imageVector = if (screenState.value?.isRecording == true)
                            Icons.Default.MicOff
                        else
                            Icons.Default.Mic,
                        contentDescription = "Start or pause recording",
                        modifier = Modifier
                            .size(100.dp)
                            .background(
                                MaterialTheme.colorScheme.primaryContainer,
                                CircleShape
                            )
                            .padding(8.dp)
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            if (records.value.isNullOrEmpty().not())
                items(records.value!!) { record ->
                    Log.e("CreateCard", "Card id: ${record.path}")
                    RecordCard(
                        fileName = record.nameWithoutExtension,
                        fileLengt = "0:00",
                        onPlay = { vm.startOrStopPlaying(File(record.path)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            else {
                item {
                    Text(
                        text = "Ещё не создано ни одной записи",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }
    }
}
























