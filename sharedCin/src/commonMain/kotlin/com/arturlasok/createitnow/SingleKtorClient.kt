package com.arturlasok.createitnow

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class SingleKtorClient() {
 private val kClient : HttpClient
     = HttpClient()
     {
         install(ContentNegotiation) {
             json(Json {
                 ignoreUnknownKeys = true
                 useAlternativeNames = false
             })
         }
     }
    suspend fun getServerTime() : String {
        return kClient.get("http://server873539.nazwa.pl/servertime-plu").bodyAsText()
    }
}