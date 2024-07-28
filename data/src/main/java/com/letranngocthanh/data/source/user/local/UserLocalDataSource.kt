package com.letranngocthanh.data.source.user.local

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto

interface UserLocalDataSource {

    suspend fun getUsers(limit: Int, offset: Int): List<UserDto>

    suspend fun saveUsers(users: List<UserDto>)

    suspend fun getUserDetail(userId: String): UserDetailDto?

    suspend fun saveUserDetail(userDetail: UserDetailDto)
}

class DefaultUserLocalDataSource(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun getUsers(limit: Int, offset: Int): List<UserDto> {
        return userDao.getUsersPaginated(limit, offset).map { it.toDto() }
    }

    override suspend fun saveUsers(users: List<UserDto>) {
        val userEntities = users.mapIndexed { index, userDto ->
            userDto.toEntity(index = index)
        }
        userDao.insertUsers(userEntities)
    }

    override suspend fun getUserDetail(userId: String): UserDetailDto? {
        return userDao.getUserDetail(userId)?.toDto()
    }

    override suspend fun saveUserDetail(userDetail: UserDetailDto) {
        userDao.insertUserDetail(userDetail.toEntity())
    }
}
