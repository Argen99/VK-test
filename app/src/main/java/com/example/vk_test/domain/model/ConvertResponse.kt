package com.example.vk_test.domain.model

data class ConvertResponse(
    val base: String,
    val amount: Int,
    val result: Map<String, Double>,
    val ms: Int
)
