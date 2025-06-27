package com.example.ktorcomposeapp.di

import com.example.ktorcomposeapp.data.api.HelloApi
import com.example.ktorcomposeapp.data.api.HelloApiImpl
import com.example.ktorcomposeapp.data.repository.HelloRepository
import com.example.ktorcomposeapp.data.repository.HelloRepositoryImpl
import com.example.ktorcomposeapp.viewmodel.HelloViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        HttpClient(CIO){
            install(ContentNegotiation){
                json(Json { ignoreUnknownKeys = true })
            }
            install(HttpTimeout){
                requestTimeoutMillis = 6000
                connectTimeoutMillis = 2000
                socketTimeoutMillis = 6000
            }
        }
    }

    single<HelloApi> { HelloApiImpl(get()) }

    single<HelloRepository> { HelloRepositoryImpl(get()) }

    viewModel {HelloViewModel(get())}
}