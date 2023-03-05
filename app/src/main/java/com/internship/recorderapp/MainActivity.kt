package com.internship.recorderapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.internship.recorderapp.data.local.datastore.DataStoreRepository
import com.internship.recorderapp.ui.common.ErrorScreen
import com.internship.recorderapp.ui.navigation.NavigationGraph
import com.internship.recorderapp.ui.theme.RecorderAppTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var datastore: DataStoreRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scope = CoroutineScope(Dispatchers.IO)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.INTERNET),
            0
        )

        val authLauncher = VK.login(this) { result: VKAuthenticationResult ->
            when (result) {
                is VKAuthenticationResult.Success -> {
                    scope.launch { datastore.setToLoginned() }
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                }
                is VKAuthenticationResult.Failed -> {
                    Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
                }
            }
        }

        setContent {
            val vkLogin = datastore.isUserLogined.collectAsState(initial = false)
            Log.e("TAG", "IsAuthenticated value: ${vkLogin.value}")

            RecorderAppTheme {
                val navController = rememberNavController()
                if (vkLogin.value)
                    NavigationGraph(navController = navController)
                else
                    ErrorScreen(
                        onRetry = {
                            authLauncher.launch(arrayListOf(VKScope.NOTIFICATIONS))
                        }
                    )
            }
        }
    }
}