package com.example.vk_test.data.remote.repository

import com.example.vk_test.data.base.makeNetworkRequest
import com.example.vk_test.data.remote.api_service.MainApiService
import com.example.vk_test.data.remote.model.toDomain
import com.example.vk_test.domain.either.Either
import com.example.vk_test.domain.model.ConvertResponse
import com.example.vk_test.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: MainApiService
): MainRepository {

    override fun convert(to: String, from: String, amount: Int?): Flow<Either<String, ConvertResponse>> = makeNetworkRequest {
        apiService.convert(to = to, from = from, amount = amount).toDomain()
    }
}