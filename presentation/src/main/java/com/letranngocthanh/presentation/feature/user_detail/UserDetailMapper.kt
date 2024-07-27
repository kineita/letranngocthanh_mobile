package com.letranngocthanh.presentation.feature.user_detail

import com.letranngocthanh.domain.model.user.UserDetail
import com.letranngocthanh.presentation.model.users.UserDetailUI

interface UserDetailMapper {

    fun toUserDetailUI(userDetail: UserDetail): UserDetailUI
}

class DefaultUserDetailMapper : UserDetailMapper {

    override fun toUserDetailUI(userDetail: UserDetail): UserDetailUI {
        return UserDetailUI(
            login = userDetail.login,
            avatar_url = userDetail.avatar_url,
            html_url = userDetail.html_url,
            location = userDetail.location,
            followers = userDetail.followers,
            following = userDetail.following
        )
    }
}