package com.letranngocthanh.data.source.user.local

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDetailEntity
import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.data.model.UserEntity

fun UserDto.toEntity(index: Int): UserEntity {
    return UserEntity(
        login = login,
        avatar_url = avatar_url,
        html_url = html_url,
        orderIndex = index
    )
}

fun UserDetailDto.toEntity(): UserDetailEntity {
    return UserDetailEntity(
        login = login,
        avatar_url = avatar_url,
        html_url = html_url,
        location = location,
        followers = followers,
        following = following
    )
}

fun UserEntity.toDto(): UserDto {
    return UserDto(
        login = login,
        avatar_url = avatar_url,
        html_url = html_url
    )
}

fun UserDetailEntity.toDto(): UserDetailDto {
    return UserDetailDto(
        login = login,
        avatar_url = avatar_url,
        html_url = html_url,
        location = location,
        followers = followers,
        following = following
    )
}
