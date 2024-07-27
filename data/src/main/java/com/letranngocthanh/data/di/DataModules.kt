package com.letranngocthanh.data.di

import com.letranngocthanh.data.feature.user.UserRepository
import com.letranngocthanh.data.feature.user.DefaultUserRepository
import com.letranngocthanh.data.util.NetworkConnectivityChecker
import com.letranngocthanh.data.util.NetworkConnectivityCheckerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModules = listOf(
    productionRetrofitModule,
    featureModule()
)

private fun featureModule() = module {

    single<UserRepository> {
        DefaultUserRepository(userService = get())
    }

    single<NetworkConnectivityChecker> {
        NetworkConnectivityCheckerImpl(androidContext())
    }
}