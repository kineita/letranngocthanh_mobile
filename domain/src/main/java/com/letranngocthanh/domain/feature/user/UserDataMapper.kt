package com.letranngocthanh.domain.feature.user

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.domain.model.user.User
import com.letranngocthanh.domain.model.user.UserDetail

interface UserDataMapper {

    fun toUserDetail(userDetailDto: UserDetailDto): UserDetail

    fun toUsers(usersDto: List<UserDto>): List<User>
}

open class DefaultUserDataMapper : UserDataMapper {

    override fun toUserDetail(userDetailDto: UserDetailDto): UserDetail {
        return UserDetail(
            login = userDetailDto.login,
            avatar_url = userDetailDto.avatar_url,
            html_url = userDetailDto.html_url,
            location = userDetailDto.location,
            followers = userDetailDto.followers,
            following = userDetailDto.following,
        )
    }

    override fun toUsers(usersDto: List<UserDto>): List<User> {
        return usersDto.map {
            User(
                login = it.login,
                avatar_url = it.avatar_url,
                html_url = it.html_url,
            )
        }
    }
}