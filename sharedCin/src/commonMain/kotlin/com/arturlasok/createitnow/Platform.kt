package com.arturlasok.createitnow

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform