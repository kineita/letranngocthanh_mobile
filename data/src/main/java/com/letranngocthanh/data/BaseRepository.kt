package com.letranngocthanh.data

interface BaseRepository {
    // Define any common methods or properties that all repositories might need
    suspend fun <T> safeApiCall(call: suspend () -> T): ResultWrapper<T> {
        return try {
            ResultWrapper.Success(call())
        } catch (e: Exception) {
            ResultWrapper.Error(e)
        }
    }
}