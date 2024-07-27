package com.letranngocthanh.data

interface BaseRepository {
    // Define any common methods or properties that all repositories might need
    suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> {
        return try {
            Result.success(call())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}