package com.example.ktorcomposeapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HelloResponse (
    val message: String
)