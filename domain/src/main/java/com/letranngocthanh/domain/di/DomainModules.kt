package com.letranngocthanh.domain.di

import com.letranngocthanh.domain.feature.user.DefaultUserDataMapper
import com.letranngocthanh.domain.feature.user.GetUserDetailUseCase
import com.letranngocthanh.domain.feature.user.GetUsersUseCase
import com.letranngocthanh.domain.feature.user.UserDataMapper
import org.koin.dsl.module

val domainModules = module {

    factory<UserDataMapper> {
        DefaultUserDataMapper()
    }

    factory {
        GetUserDetailUseCase(
            userRepository = get(),
            userDataMapper = get(),
            networkConnectivityChecker = get(),
        )
    }

    factory {
        GetUsersUseCase(
            userRepository = get(),
            userDataMapper = get(),
            networkConnectivityChecker = get(),
        )
    }
}