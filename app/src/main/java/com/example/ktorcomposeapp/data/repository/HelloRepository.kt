package com.example.ktorcomposeapp.data.repository

import com.example.ktorcomposeapp.data.model.HelloResponse

interface HelloRepository {
    suspend fun getHelloMessage(): HelloResponse
}