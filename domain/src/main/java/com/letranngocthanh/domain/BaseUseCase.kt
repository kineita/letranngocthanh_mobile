package com.letranngocthanh.domain

import com.letranngocthanh.data.util.NetworkConnectivityChecker
import com.letranngocthanh.domain.exception.NoInternetConnectionException

abstract class BaseUseCase<in Params, out T> {

    suspend operator fun invoke(params: Params): Result<T> {
        validateParams(params)

        return execute(params)
    }

    protected abstract suspend fun execute(params: Params): Result<T>

    protected abstract fun validateParams(params: Params)
}