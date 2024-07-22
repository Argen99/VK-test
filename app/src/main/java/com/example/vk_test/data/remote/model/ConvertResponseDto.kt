package com.example.vk_test.data.remote.model

import com.example.vk_test.domain.model.ConvertResponse

data class ConvertResponseDto(
    val base: String,
    val amount: Int,
    val result: Map<String, Double>,
    val ms: Int
)

fun ConvertResponseDto.toDomain() = ConvertResponse(
    base = base,
    amount = amount,
    result = result,
    ms = ms,
)