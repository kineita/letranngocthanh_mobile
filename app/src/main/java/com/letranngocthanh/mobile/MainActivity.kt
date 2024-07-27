package com.letranngocthanh.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.letranngocthanh.mobile.feature.user_detail.UserDetailScreen
import com.letranngocthanh.mobile.feature.users.UserListScreen
import com.letranngocthanh.mobile.navigator.NavControllerProvider
import com.letranngocthanh.mobile.navigator.NavigationRoutes
import com.letranngocthanh.mobile.navigator.getUserId
import com.letranngocthanh.mobile.ui.theme.Letranngocthanh_mobileTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Letranngocthanh_mobileTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavControllerProvider { navController ->
                        NavHost(
                            navController = navController,
                            startDestination = NavigationRoutes.USER_LIST
                        ) {
                            composable(NavigationRoutes.USER_LIST) {
                                UserListScreen(navController = navController)
                            }

                            composable(NavigationRoutes.USER_DETAIL) { backStackEntry ->
                                val userId = backStackEntry.getUserId()
                                UserDetailScreen(userId = userId, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}