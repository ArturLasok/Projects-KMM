package com.arturlasok.createitnow

import com.arturlasok.createitnow.di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KtorHelper : KoinComponent {
    private val ktor : SingleKtorClient by inject()
    suspend fun ktorData() : String = ktor.getServerTime()
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
