package com.letranngocthanh.presentation.feature.users

import com.letranngocthanh.domain.model.user.User
import com.letranngocthanh.presentation.model.users.UserUI

interface UserUIMapper {

    fun toUserUI(user: User): UserUI

    fun toUserUIs(users: List<User>): List<UserUI>
}

class DefaultUserUIMapper : UserUIMapper {

    override fun toUserUI(user: User): UserUI {
        return UserUI(
            login = user.login,
            avatar_url = user.avatar_url,
            html_url = user.html_url,
        )
    }

    override fun toUserUIs(users: List<User>): List<UserUI> {
        return users.map {
            toUserUI(it)
        }
    }
}