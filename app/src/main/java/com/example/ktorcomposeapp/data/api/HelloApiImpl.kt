package com.example.ktorcomposeapp.data.api

import com.example.ktorcomposeapp.data.model.HelloResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CancellationException

class HelloApiImpl(private val client: HttpClient) : HelloApi {
    override suspend fun getHelloMessage(): HelloResponse {
        return try {
            client.get("http://192.168.0.24:8080/helloworld").body()
        } catch (e: Exception){
            when (e){
                is CancellationException -> throw e
                is HttpRequestTimeoutException -> throw Exception("Request timed out!")
                else -> throw Exception("Error: ${e.message}")
            }
        }
    }
}