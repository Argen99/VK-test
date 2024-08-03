package com.example.vk_test.data.remote.api_service

import com.example.vk_test.data.remote.model.ConvertResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {

    @GET(CONVERT_ENDPOINT)
    suspend fun convert(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("from") from: String = "RUB",
        @Query("to") to: String,
        @Query("amount") amount: Int?,
    ) : ConvertResponseDto

    companion object {
        const val API_KEY = "b3e4f35203-ee5d6d154c-shn1p0"
        const val CONVERT_ENDPOINT = "convert"
    }
}