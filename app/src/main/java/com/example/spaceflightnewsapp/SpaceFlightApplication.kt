package com.example.spaceflightnewsapp

import android.app.Application
import com.example.spaceflightnewsapp.inject.networkModule
import com.example.spaceflightnewsapp.inject.repositoryModule
import com.example.spaceflightnewsapp.inject.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class SpaceFlightApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpaceFlightApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}