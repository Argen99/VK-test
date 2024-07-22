package com.example.vk_test.data.di

import com.example.vk_test.data.remote.api_service.MainApiService
import com.example.vk_test.domain.repository.MainRepository
import com.example.vk_test.data.remote.repository.MainRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.fastforex.io/"

val dataModule = module {
    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    singleOf(::provideRetrofit)
    singleOf(::provideOkHttpClient)
    singleOf(::provideMainApi)
    singleOf(::MainRepositoryImpl) {
        bind<MainRepository>()
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideMainApi(retrofit: Retrofit): MainApiService {
    return retrofit.create(MainApiService::class.java)
}