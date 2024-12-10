package com.example.littlelemon

import androidx.room.Room
import com.example.littlelemon.data.local.AppDatabase
import com.example.littlelemon.data.remote.LittleLemonApi
import com.example.littlelemon.data.repository.AppRepository
import com.example.littlelemon.data.repository.PreferenceRepository
import com.example.littlelemon.presentation.viewmodel.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.*
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {

    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true // Optional: Ignore unknown keys in JSON
                })
            }
            // ... other Ktor configurations (e.g., logging, timeouts) ...
        }
    }

    single<AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "little-lemon-database"
        ).build()
    }

    single { LittleLemonApi() }

    single { get<AppDatabase>().getMenuItemDao() }

    single { AppRepository(get(), get()) }

    single { PreferenceRepository(androidContext()) }

    viewModel { HomeViewModel(get(), get(), get()) }

}