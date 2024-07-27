package com.letranngocthanh.presentation.feature.users

import androidx.navigation.NavHostController

interface UsersListNavigator {

    fun navigateToUserDetail(userLogin: String)
}

open class DefaultUsersListNavigator(
    private val navController: NavHostController,
) : UsersListNavigator {

    override fun navigateToUserDetail(userLogin: String) {
        navController.navigate("userDetail/$userLogin")
    }
}