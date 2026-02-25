package com.example.spaceflightnewsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightnewsapp.repository.NewsRepository
import kotlinx.coroutines.launch

// Manual constructor injection
// TODO: Hilt DI
class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {

    private val _newsState = MutableLiveData<NewsState>(NewsState.Loading)
    val newsState: LiveData<NewsState> = _newsState

    // 2 Methods - 1) LaunchedEffect 2) init

    init {
        fetchNews()
    }

    private fun fetchNews(){
        viewModelScope.launch {
            _newsState.value = NewsState.Loading
            val result = newsRepository.getNews()
            _newsState.value = if(result.isSuccess){
                val news = result.getOrNull() ?: emptyList()
                NewsState.Success(news)
            } else {
                NewsState.Error(result.exceptionOrNull() ?.message ?: "Unknown Error")
            }
        }
    }
}
