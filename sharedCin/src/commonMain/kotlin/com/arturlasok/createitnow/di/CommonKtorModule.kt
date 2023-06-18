package com.arturlasok.createitnow.di

import com.arturlasok.createitnow.SingleKtorClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonKtorModule = module { singleOf(::SingleKtorClient) }