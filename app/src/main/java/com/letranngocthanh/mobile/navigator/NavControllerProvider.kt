package com.letranngocthanh.mobile.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun NavControllerProvider(content: @Composable (NavHostController) -> Unit) {
    val navController = rememberNavController()
    content(navController)
}