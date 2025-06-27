package com.example.ktorcomposeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorcomposeapp.data.model.HelloResponse
import com.example.ktorcomposeapp.data.repository.HelloRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class HelloViewModel(
    private val repository: HelloRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState : StateFlow<UiState> = _uiState

    /*init {
        fetchHelloMessage()
    }*/

    fun fetchHelloMessage(){
        viewModelScope.launch {
            _uiState.value=UiState.Loading
            try {
                val response: HelloResponse = repository.getHelloMessage()
                _uiState.value = UiState.Success(response.message)

            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message?:"Unknown error")
            }
        }

    }
}