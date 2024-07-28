package com.letranngocthanh.data.source.user.remote

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto

interface UserRemoteDataSource {

    suspend fun getUsers(perPage: Int, since: Int): List<UserDto>

    suspend fun getUserDetail(loginUsername: String): UserDetailDto
}

class DefaultUserRemoteDataSource(
    private val userService: UserService
) : UserRemoteDataSource {

    override suspend fun getUsers(perPage: Int, since: Int): List<UserDto> {
        return userService.getUsers(perPage = perPage, since = since)
    }

    override suspend fun getUserDetail(loginUsername: String): UserDetailDto {
        return userService.getUserDetail(loginUsername = loginUsername)
    }
}