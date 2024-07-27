package com.letranngocthanh.domain.feature.user

import com.letranngocthanh.data.feature.user.UserRepository
import com.letranngocthanh.data.util.NetworkConnectivityChecker
import com.letranngocthanh.domain.BaseUseCase
import com.letranngocthanh.domain.exception.pagination.InvalidPageNumberException
import com.letranngocthanh.domain.exception.pagination.InvalidPageSizeException
import com.letranngocthanh.domain.model.user.User

class GetUsersUseCase(
    private val userRepository: UserRepository,
    private val userDataMapper: UserDataMapper,
    networkConnectivityChecker: NetworkConnectivityChecker
) : BaseUseCase<GetUsersUseCase.Params, List<User>>(networkConnectivityChecker) {

    override suspend fun execute(params: Params): Result<List<User>> {
        return userRepository.getUsers(
            perPage = params.perPage,
            since = params.since
        ).mapCatching { usersDto ->
            userDataMapper.toUsers(usersDto = usersDto)
        }
    }

    data class Params(
        val perPage: Int = 20,
        val since: Int
    )

    override fun validateParams(params: Params) {
        if (params.since < 0) throw InvalidPageNumberException()
        if (params.perPage <= 0) throw InvalidPageSizeException()
    }
}
