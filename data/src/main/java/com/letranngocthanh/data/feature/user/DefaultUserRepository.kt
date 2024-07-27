package com.letranngocthanh.data.feature.user

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultUserRepository(
    private val userService: UserService,
) : UserRepository {

    override suspend fun getUsers(perPage: Int, since: Int): Result<List<UserDto>> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                userService.getUsers(perPage = perPage, since = since)
            }
        }
    }

    override suspend fun getUserDetail(userId: String): Result<UserDetailDto> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                userService.getUserDetails(loginUsername = userId)
            }
        }
    }
}