package com.letranngocthanh.data.repo.user

import com.letranngocthanh.data.BaseRepository
import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto

interface UserRepository : BaseRepository {

    suspend fun getUsers(perPage: Int, since: Int): Result<List<UserDto>>

    suspend fun getUserDetail(userId: String): Result<UserDetailDto>
}