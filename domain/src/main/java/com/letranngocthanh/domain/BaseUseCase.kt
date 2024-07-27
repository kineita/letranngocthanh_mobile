package com.letranngocthanh.domain

import com.letranngocthanh.data.util.NetworkConnectivityChecker
import com.letranngocthanh.domain.exception.NoInternetConnectionException

abstract class BaseUseCase<in Params, out T>(
    private val networkConnectivityChecker: NetworkConnectivityChecker,
) {

    suspend operator fun invoke(params: Params): Result<T> {
        return if (networkConnectivityChecker.isInternetAvailable()) {
            validateParams(params)
            execute(params)
        } else {
            Result.failure(NoInternetConnectionException)
        }
    }

    protected abstract suspend fun execute(params: Params): Result<T>

    protected abstract fun validateParams(params: Params)
}