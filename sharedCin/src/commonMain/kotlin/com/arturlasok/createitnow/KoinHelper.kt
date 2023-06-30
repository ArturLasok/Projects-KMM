package com.arturlasok.createitnow

import com.arturlasok.createitnow.di.appModule
import com.arturlasok.createitnow.model.KMMAppData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KtorHelper : KoinComponent {
    private val ktor : SingleKtorClient by inject()
    suspend fun ktorData() : String = ktor.getServerTime()
    suspend fun ktorAppsData() : List<KMMAppData> = ktor.getAppsData()
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
