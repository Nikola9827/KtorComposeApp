package com.example.ktorcomposeapp.viewmodel


sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success (val message: String) : UiState()
    data class Error (val errorMessage: String) : UiState()
}