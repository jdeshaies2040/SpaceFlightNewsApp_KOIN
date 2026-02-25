package com.example.spaceflightnewsapp.api

import com.example.spaceflightnewsapp.model.NewsResponse
import retrofit2.http.GET

// https://api.spaceflightnewsapi.net/v4/articles/?format=json
interface NewsApi {
    @GET("articles/?format=json")
    suspend fun getNews(): NewsResponse
}
