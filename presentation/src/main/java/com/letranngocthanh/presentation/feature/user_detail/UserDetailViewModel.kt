package com.letranngocthanh.presentation.feature.user_detail

import com.letranngocthanh.domain.feature.user.GetUserDetailUseCase
import com.letranngocthanh.presentation.BaseViewModel
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.exception.user_detail.FetchUserDetailException
import com.letranngocthanh.presentation.model.users.UserDetailUI

open class UserDetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val userDetailMapper: UserDetailMapper,
) : BaseViewModel<UserDetailUI>() {

    fun fetchUserDetail(userId: String) {
        launch({
            val result = getUserDetailUseCase(GetUserDetailUseCase.Params(userId = userId)).map {
                userDetailMapper.toUserDetailUI(userDetail = it)
            }
            val userDetailUI = result.getOrNull()
            if (result.isSuccess && userDetailUI != null) {
                _viewState.value = ViewState.Success(userDetailUI)
            } else {
                val exception = result.exceptionOrNull() ?: FetchUserDetailException()
                handleError(exception)
            }
        })
    }

    companion object {
        private const val TAG = "UserDetailViewModel"
    }
}