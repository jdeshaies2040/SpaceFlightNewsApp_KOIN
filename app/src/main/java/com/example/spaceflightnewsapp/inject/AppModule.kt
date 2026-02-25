package com.example.spaceflightnewsapp.inject

import com.example.spaceflightnewsapp.api.NewsApi
import com.example.spaceflightnewsapp.repository.NewsRepository
import com.example.spaceflightnewsapp.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.spaceflightnewsapi.net/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}

val repositoryModule = module {
    single { NewsRepository(get()) }
}

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}