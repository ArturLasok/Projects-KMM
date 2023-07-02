package com.arturlasok.createitnow

import com.arturlasok.createitnow.model.KMMAppData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class SingleKtorClient {
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
    suspend fun getAppsData() : List<KMMAppData> {

        return try {
            val response: HttpResponse = kClient.get("http://server873539.nazwa.pl/kmmappdata")

            val json = response.body<List<KMMAppData>>()
            json
        } catch(e:Exception) {
            //do nothing
            listOf()
        }

    }
    fun getPlatformForIos(linkAnd: String, linkIos: String, linkWeb: String) : String {
        var retString =""
        val platform = listOf<Pair<String,String>>(Pair(linkAnd,"Android"), Pair(linkIos,"iOS"),Pair(linkWeb,"Web"))

        platform.onEach {platform ->
            if(platform.first.isNotEmpty()) {

                retString += platform.second
            }

        }

        return retString
    }

}