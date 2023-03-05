package com.internship.recorderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.internship.recorderapp.ui.navigation.NavigationGraph
import com.internship.recorderapp.ui.theme.RecorderAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )

        setContent {
            RecorderAppTheme {
                val navController = rememberNavController()

                NavigationGraph(navController = navController)
            }
        }
    }
}