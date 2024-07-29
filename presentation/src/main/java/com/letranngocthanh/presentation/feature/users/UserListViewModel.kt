package com.letranngocthanh.presentation.feature.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.letranngocthanh.domain.feature.user.GetUsersUseCase
import com.letranngocthanh.presentation.BaseViewModel
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.exception.users.FetchUsersException
import com.letranngocthanh.presentation.model.users.UserUI
import timber.log.Timber

open class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val userUIMapper: UserUIMapper,
) : BaseViewModel<List<UserUI>>() {

    private val _loadingMore = mutableStateOf(false)
    val loadingMore: State<Boolean> = _loadingMore

    private val _uiEvent = mutableStateOf<UiEvent>(UiEvent.None)
    val uiEvent: State<UiEvent> get() = _uiEvent

    private var currentPage = 0
    private var usersList = mutableListOf<UserUI>()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        launch({
            _viewState.value = ViewState.Loading

            val result = getUsersUseCase(
                GetUsersUseCase.Params(
                    perPage = ITEMS_PER_PAGE,
                    since = currentPage * ITEMS_PER_PAGE
                )
            )
            if (result.isSuccess) {
                val userUIs = userUIMapper.toUserUIs(result.getOrNull() ?: emptyList())
                Timber.d("$TAG - fetchUsers successfully - $userUIs")
                usersList.addAll(userUIs)
                _viewState.value = ViewState.Success(usersList)
                currentPage++
            } else {
                val exception = result.exceptionOrNull() ?: FetchUsersException()
                Timber.d("$TAG - fetchUsers fail - $exception")
                _viewState.value = ViewState.Error(exception)
            }
        })
    }

    fun onBottomReached() {
        if (_loadingMore.value) return

        _loadingMore.value = true
        launch({
            val result = getUsersUseCase(
                GetUsersUseCase.Params(
                    perPage = ITEMS_PER_PAGE,
                    since = currentPage * ITEMS_PER_PAGE
                )
            )
            if (result.isSuccess) {
                val userUIs = userUIMapper.toUserUIs(result.getOrNull() ?: emptyList())
                usersList.addAll(userUIs)
                _viewState.value = ViewState.Success(usersList)
                currentPage++
            } else {
                val exception = result.exceptionOrNull() ?: FetchUsersException()
                Timber.d("$TAG - fetchUsers fail - $exception")
                _uiEvent.value = UiEvent.ShowToast("Failed to load more users: ${exception.message}")
            }
            _loadingMore.value = false
        })
    }

    companion object {
        private const val TAG = "UserListViewModel"

        private const val ITEMS_PER_PAGE = 20
    }
}

sealed class UiEvent {

    data class ShowToast(val message: String) : UiEvent()

    object None : UiEvent()
}