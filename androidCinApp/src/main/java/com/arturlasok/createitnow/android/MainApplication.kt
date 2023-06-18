package com.arturlasok.createitnow.android

import android.app.Application
import com.arturlasok.createitnow.android.di.androidModule
import com.arturlasok.createitnow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModule()+androidModule)
        }
    }
}