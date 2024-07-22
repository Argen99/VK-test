package com.example.vk_test.presentation.di

import org.koin.dsl.module
import com.example.vk_test.presentation.ui.fragments.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf

val presentationModule = module {

    viewModelOf(::MainViewModel)
}