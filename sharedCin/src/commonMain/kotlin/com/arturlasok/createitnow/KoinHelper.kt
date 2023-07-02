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
    fun platformForIos(linkAnd:String,linkIos: String, linkWeb: String) :String  {  return ktor.getPlatformForIos(linkAnd,linkIos,linkWeb) }
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
