package com.letranngocthanh.presentation.feature.user_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.letranngocthanh.domain.feature.user.GetUserDetailUseCase
import com.letranngocthanh.presentation.BaseViewModel
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.model.users.UserDetailUI
import kotlinx.coroutines.launch

open class UserDetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val userDetailMapper: UserDetailMapper,
) : BaseViewModel() {

    private val _viewState = mutableStateOf<ViewState<UserDetailUI>>(ViewState.Loading)
    val viewState: State<ViewState<UserDetailUI>> = _viewState

    fun fetchUserDetail(userId: String) {
        viewModelScope.launch {
            val result = getUserDetailUseCase(GetUserDetailUseCase.Params(userId = userId)).map {
                userDetailMapper.toUserDetailUI(userDetail = it)
            }
            val userDetailUI = result.getOrNull()

            if (result.isSuccess && userDetailUI != null) {
                _viewState.value = ViewState.Success(userDetailUI)
            } else {
                _viewState.value = ViewState.Error(Exception("Unknown error"))
            }
        }
    }

    companion object {
        private const val TAG = "UserDetailViewModel"
    }
}