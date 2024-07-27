package com.letranngocthanh.mobile.di

import com.letranngocthanh.data.di.dataModules
import com.letranngocthanh.domain.di.domainModules
import com.letranngocthanh.presentation.di.presentationModules

val appModules = mutableListOf(
    presentationModules,
    domainModules,
).apply {
    addAll(dataModules)

    // Add your override DI below
    // add(custom base on market or something)
}