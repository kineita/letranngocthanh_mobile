package com.letranngocthanh.presentation

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> get() = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> get() = _error

    protected fun launchDataLoad(
        block: suspend () -> Unit,
        onError: (Throwable) -> Unit = { handleError(it) }
    ) {
        _loading.value = true
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                block()
            } catch (e: Exception) {
                onError(e)
            } finally {
                _loading.value = false
            }
        }
    }

    private fun handleError(throwable: Throwable) {
        _error.value = throwable.message
    }
}
