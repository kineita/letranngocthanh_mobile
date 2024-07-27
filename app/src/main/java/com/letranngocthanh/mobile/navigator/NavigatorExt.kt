package com.letranngocthanh.mobile.navigator

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

fun NavBackStackEntry.getUserId(): String {
    return arguments?.getString(NavigationParams.USER_ID) ?: ""
}