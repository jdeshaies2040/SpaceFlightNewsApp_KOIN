package com.example.spaceflightnewsapp.viewmodel

sealed class NewsState {
    object Loading : NewsState()
    data class Success(val news: List<com.example.spaceflightnewsapp.model.Result>): NewsState()
    data class Error(val message: String): NewsState()
}
