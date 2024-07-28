package com.letranngocthanh.data.source.user.remote

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int,
        @Query("since") since: Int
    ): List<UserDto>

    @GET("users/{login_username}")
    suspend fun getUserDetail(
        @Path("login_username") loginUsername: String
    ): UserDetailDto
}