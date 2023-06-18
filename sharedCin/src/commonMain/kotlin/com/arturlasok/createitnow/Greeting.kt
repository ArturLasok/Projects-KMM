package com.arturlasok.createitnow

import io.ktor.client.HttpClient

class Greeting {

    private val platform: Platform = getPlatform()
    fun greet(): String {
        return "My Projects in Android, iOS and Web.\nNow in ${platform.name}! \nMy KTOR server time is:\n"
    }
}