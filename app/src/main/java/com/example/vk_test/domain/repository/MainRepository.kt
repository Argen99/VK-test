package com.example.vk_test.domain.repository

import com.example.vk_test.domain.either.Either
import com.example.vk_test.domain.model.ConvertResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun convert(to: String,from: String, amount: Int?): Flow<Either<String, ConvertResponse>>
}