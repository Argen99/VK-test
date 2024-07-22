package com.example.vk_test.data.base

import com.example.vk_test.domain.either.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal fun <T> makeNetworkRequest(
    request: suspend () -> T
) =
    flow<Either<String, T>> {
        request().also {
            emit(Either.Right(value = it))
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(Either.Left(value = exception.message ?: "Error Occurred!"))
    }