package com.letranngocthanh.presentation.feature.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.letranngocthanh.domain.feature.user.GetUsersUseCase
import com.letranngocthanh.presentation.BaseViewModel
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.model.users.UserUI
import com.letranngocthanh.presentation.viewState
import kotlinx.coroutines.launch
import timber.log.Timber

open class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val userUIMapper: UserUIMapper,
) : BaseViewModel() {

    private val _viewState = mutableStateOf<ViewState<List<UserUI>>>(ViewState.Loading)
    val viewState: State<ViewState<List<UserUI>>> = _viewState

    private val _loadingMore = mutableStateOf(false)
    val loadingMore: State<Boolean> = _loadingMore

    private var currentPage = 0
    private var usersList = mutableListOf<UserUI>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading

            val result = getUsersUseCase(GetUsersUseCase.Params(perPage = 20, since = currentPage * 20))
            if (result.isSuccess) {
                val userUIs = userUIMapper.toUserUIs(result.getOrNull() ?: emptyList())
                Timber.d("$TAG - fetchUsers successfully - $userUIs")
                usersList.addAll(userUIs)
                _viewState.value = ViewState.Success(usersList)
                currentPage++
            } else {
                val exception = result.exceptionOrNull() ?: Exception("Unknown Error - Cannot fetchUsers")
                Timber.d("$TAG - fetchUsers fail - $exception")
                _viewState.value = ViewState.Error(exception)
            }
        }
    }

    fun onBottomReached() {
        if (_loadingMore.value) return

        _loadingMore.value = true
        viewModelScope.launch {
            val result = getUsersUseCase(GetUsersUseCase.Params(perPage = 20, since = currentPage * 20))
            if (result.isSuccess) {
                val userUIs = userUIMapper.toUserUIs(result.getOrNull() ?: emptyList())
                usersList.addAll(userUIs)
                _viewState.value = ViewState.Success(usersList)
                currentPage++
            }
            _loadingMore.value = false
        }
    }

    companion object {
        private const val TAG = "UserListViewModel"
    }
}