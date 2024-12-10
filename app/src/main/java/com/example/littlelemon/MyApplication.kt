package com.example.littlelemon

import android.app.Application
import android.content.ContentResolver
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    companion object {
        var myContentResolver: ContentResolver? = null
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(databaseModule)
        }
        myContentResolver  = contentResolver
    }

}