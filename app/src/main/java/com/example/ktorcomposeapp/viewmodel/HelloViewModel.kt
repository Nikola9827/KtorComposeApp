package com.example.ktorcomposeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorcomposeapp.HelloResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class HelloViewModel : ViewModel(){
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState : StateFlow<UiState> = _uiState

    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json { ignoreUnknownKeys = true })
        }
        install(HttpTimeout){
            requestTimeoutMillis = 6000
            connectTimeoutMillis = 2000
            socketTimeoutMillis = 6000
        }

    }

    /*init {
        fetchHelloMessage()
    }*/

    fun fetchHelloMessage(){
        viewModelScope.launch {
            _uiState.value=UiState.Loading
            try {
                val response: HelloResponse = client.get("http://192.168.0.25:8080/helloworld").body()
                _uiState.value = UiState.Success(response.message)

            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> throw e
                    is HttpRequestTimeoutException -> _uiState.value = UiState.Error("Request timed out!")
                    else -> _uiState.value = UiState.Error("Error: ${e.message}")
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        client.close()
    }
}