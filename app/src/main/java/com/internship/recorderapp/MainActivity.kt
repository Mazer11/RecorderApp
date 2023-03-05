package com.internship.recorderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.internship.recorderapp.ui.navigation.NavigationGraph
import com.internship.recorderapp.ui.theme.RecorderAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecorderAppTheme {
                val navController = rememberNavController()

                NavigationGraph(navController = navController)
            }
        }
    }
}