package com.example.ktorcomposeapp

import kotlinx.serialization.Serializable

@Serializable
data class HelloResponse (
    val message: String
)