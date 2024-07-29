package com.letranngocthanh.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    protected val _viewState = mutableStateOf<ViewState<T>>(ViewState.Loading)
    val viewState: State<ViewState<T>> = _viewState

    protected fun launch(
        block: suspend () -> Unit,
        onError: (Throwable) -> Unit = { handleError(it) }
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        viewModelScope.launch(exceptionHandler) {
            try {
                block()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected open fun handleError(throwable: Throwable) {
        _viewState.value = ViewState.Error(throwable)
    }
}