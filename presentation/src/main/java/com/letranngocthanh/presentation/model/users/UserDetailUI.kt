package com.letranngocthanh.presentation.model.users

data class UserDetailUI(
    val login: String,
    val avatar_url: String,
    val html_url: String,
    val location: String?,
    val followers: Int,
    val following: Int
)
