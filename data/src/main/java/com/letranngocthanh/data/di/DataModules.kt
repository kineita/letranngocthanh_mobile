package com.letranngocthanh.data.di

import com.letranngocthanh.data.repo.user.UserRepository
import com.letranngocthanh.data.repo.user.DefaultUserRepository
import com.letranngocthanh.data.source.user.local.DefaultUserLocalDataSource
import com.letranngocthanh.data.source.user.local.UserDao
import com.letranngocthanh.data.source.user.local.UserDatabase
import com.letranngocthanh.data.source.user.local.UserLocalDataSource
import com.letranngocthanh.data.source.user.remote.DefaultUserRemoteDataSource
import com.letranngocthanh.data.source.user.remote.UserRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModules = listOf(
    productionRetrofitModule,
    roomModule(),
    featureModule(),
)

private fun featureModule() = module {

    single<UserRepository> {
        DefaultUserRepository(
            remoteDataSource = get(),
            localDataSource = get(),
        )
    }

    single<UserRemoteDataSource> {
        DefaultUserRemoteDataSource(
            userService = get()
        )
    }

    single<UserLocalDataSource> {
        DefaultUserLocalDataSource(
            userDao = get()
        )
    }
}

private fun roomModule() = module {
    single { UserDatabase.getDatabase(androidContext()) }
    single<UserDao> { get<UserDatabase>().userDao() }
}