package com.letranngocthanh.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val login: String,
    val avatar_url: String,
    val html_url: String,
    val orderIndex: Int,
)

@Entity(tableName = "user_detail")
data class UserDetailEntity(
    @PrimaryKey val login: String,
    val avatar_url: String,
    val html_url: String,
    val location: String?,
    val followers: Int,
    val following: Int
)