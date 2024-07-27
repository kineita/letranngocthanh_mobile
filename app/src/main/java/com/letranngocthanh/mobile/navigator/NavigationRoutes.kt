package com.letranngocthanh.mobile.navigator

object NavigationParams {
    const val USER_ID = "userId"
}

object NavigationRoutes {
    const val USER_LIST = "userList"
    const val USER_DETAIL = "userDetail/{${NavigationParams.USER_ID}}"

    fun userDetailRoute(userId: String) = "userDetail/$userId"
}