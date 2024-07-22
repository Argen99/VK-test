package com.example.vk_test.presentation

import android.app.Application
import com.example.vk_test.data.di.dataModule
import com.example.vk_test.presentation.di.presentationModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(presentationModule, dataModule)
        }
    }
}