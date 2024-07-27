package com.letranngocthanh.presentation

sealed class ViewState<out T> {
    data class Error(val t: Throwable) : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    object Loading : ViewState<Nothing>()

    fun merge(other: ViewState<*>?): ViewState<*>? = when {
        this is Success<*> && other is Success<*> -> Success(this.data)
        this is Error -> this
        other is Error -> other
        this is Loading -> Loading
        other is Loading -> Loading
        else -> null
    }
}

val <T> Result<T>.viewState: ViewState<T>?
    get() = when {
        isSuccess -> ViewState.Success(getOrNull() ?: throw IllegalStateException("Result is successful but data is null"))
        isFailure -> ViewState.Error(exceptionOrNull() ?: Exception("Unknown error"))
        else -> null
    }