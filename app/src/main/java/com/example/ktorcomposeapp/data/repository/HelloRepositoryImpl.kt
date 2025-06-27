package com.example.ktorcomposeapp.data.repository

import com.example.ktorcomposeapp.data.api.HelloApi
import com.example.ktorcomposeapp.data.model.HelloResponse

class HelloRepositoryImpl(
    private val api: HelloApi
) : HelloRepository {
    override suspend fun getHelloMessage(): HelloResponse {
        return api.getHelloMessage()
    }
}