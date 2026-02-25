package com.example.spaceflightnewsapp.repository

import com.example.spaceflightnewsapp.api.NewsApi
import com.example.spaceflightnewsapp.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Manual constructor injection
// TODO: Hilt DI
class NewsRepository(private val api: NewsApi = RetrofitClient.api) {
    suspend fun getNews(): Result<List<com.example.spaceflightnewsapp.model.Result>> = withContext(Dispatchers.IO){
        try {
            val response = api.getNews()
            val news = response.results
            Result.success(news)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}
