package com.letranngocthanh.data.repo.user

import com.letranngocthanh.data.exception.user.CannotFetchUserDetailInLocalAndRemoteDataSource
import com.letranngocthanh.data.exception.user.CannotFetchUsersInLocalAndRemoteDataSource
import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.data.source.user.local.UserLocalDataSource
import com.letranngocthanh.data.source.user.remote.UserRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultUserRepository(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getUsers(perPage: Int, since: Int): Result<List<UserDto>> {
        return withContext(Dispatchers.IO) {
            val remoteResult = safeApiCall {
                val userDtoList = remoteDataSource.getUsers(perPage = perPage, since = since)
                localDataSource.saveUsers(users = userDtoList)
                userDtoList
            }

            if (remoteResult.isFailure) {
                safeApiCall {
                    val localData = localDataSource.getUsers(perPage, since)
                    if (localData.isEmpty()) throw CannotFetchUsersInLocalAndRemoteDataSource()
                    localData
                }
            } else {
                remoteResult
            }
        }
    }

    override suspend fun getUserDetail(userId: String): Result<UserDetailDto> {
        return withContext(Dispatchers.IO) {
            val remoteResult = safeApiCall {
                val userDetailDto = remoteDataSource.getUserDetail(loginUsername = userId)
                localDataSource.saveUserDetail(userDetail = userDetailDto)
                userDetailDto
            }

            if (remoteResult.isFailure) {
                safeApiCall {
                    localDataSource.getUserDetail(userId) ?: throw CannotFetchUserDetailInLocalAndRemoteDataSource()
                }
            } else {
                remoteResult
            }
        }
    }
}