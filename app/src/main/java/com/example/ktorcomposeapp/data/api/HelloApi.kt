package com.example.ktorcomposeapp.data.api

import com.example.ktorcomposeapp.data.model.HelloResponse

interface HelloApi {
    suspend fun getHelloMessage(): HelloResponse
}