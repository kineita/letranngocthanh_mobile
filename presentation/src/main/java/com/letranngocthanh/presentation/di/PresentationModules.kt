package com.letranngocthanh.presentation.di

import com.letranngocthanh.presentation.feature.user_detail.DefaultUserDetailMapper
import com.letranngocthanh.presentation.feature.user_detail.UserDetailMapper
import com.letranngocthanh.presentation.feature.user_detail.UserDetailViewModel
import com.letranngocthanh.presentation.feature.users.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {

    viewModel {
        UserListViewModel(
            getUsersUseCase = get(),
            userUIMapper = get(),
        )
    }

    viewModel {
        UserDetailViewModel(
            getUserDetailUseCase = get(),
            userDetailMapper = get(),
        )
    }

    factory<UserUIMapper> {
        DefaultUserUIMapper()
    }

    factory<UserDetailMapper> {
        DefaultUserDetailMapper()
    }
}