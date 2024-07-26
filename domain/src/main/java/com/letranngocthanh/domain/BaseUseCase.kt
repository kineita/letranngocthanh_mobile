package com.letranngocthanh.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class BaseUseCase<in P, R> {

    abstract suspend fun execute(parameters: P): R

    suspend fun run(parameters: P): Result<R> {
        return try {
            Result.Success(execute(parameters))
        } catch (exception: Throwable) {
            Result.Error(exception)
        }
    }
}