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
        const val API_KEY = "73499f1cb9-b817cc36f3-sgyv21"
        const val CONVERT_ENDPOINT = "convert"
    }
}