package com.letranngocthanh.domain.feature.user

import com.letranngocthanh.data.repo.user.UserRepository
import com.letranngocthanh.data.util.NetworkConnectivityChecker
import com.letranngocthanh.domain.BaseUseCase
import com.letranngocthanh.domain.exception.user.InvalidUserIdException
import com.letranngocthanh.domain.model.user.UserDetail

class GetUserDetailUseCase(
    private val userRepository: UserRepository,
    private val userDataMapper: UserDataMapper,
) : BaseUseCase<GetUserDetailUseCase.Params, UserDetail>() {

    override suspend fun execute(params: Params): Result<UserDetail> {
        return userRepository.getUserDetail(userId = params.userId).mapCatching {
            userDataMapper.toUserDetail(userDetailDto = it)
        }
    }

    data class Params(val userId: String)

    override fun validateParams(params: Params) {
        if (params.userId.isEmpty()) throw InvalidUserIdException()
    }
}