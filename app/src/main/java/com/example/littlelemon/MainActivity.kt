package com.example.littlelemon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.data.local.AppDatabase
import com.example.littlelemon.data.local.MenuItemRoom
import com.example.littlelemon.data.remote.MenuNetworkData
import com.example.littlelemon.data.repository.PreferenceRepository
import com.example.littlelemon.presentation.viewmodel.HomeViewModel
import com.example.littlelemon.ui.navigation.Destinations
import com.example.littlelemon.ui.navigation.NavEvent
import com.example.littlelemon.ui.navigation.Navigation
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferenceRepository =
            PreferenceRepository.getPreferenceRepository(this.applicationContext)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val homeViewModel by viewModel<HomeViewModel>()
                    val state by homeViewModel.state.collectAsStateWithLifecycle()

                    Navigation(
                        navController = navController,
                        state = state,
                        onEvent = homeViewModel::onEvent,
                        preferenceRepository = preferenceRepository)

                    LaunchedEffect(Unit) {
                        homeViewModel.outputEvent.collectLatest { outputEvent ->
                            when (outputEvent) {
                                is HomeViewModel.OutputEvent.ShowMessage ->
                                    this@MainActivity.toast(
                                        outputEvent.message
                                    )

                                is HomeViewModel.OutputEvent.Logged -> {
                                    navController.navigate(Destinations.Home.route)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}