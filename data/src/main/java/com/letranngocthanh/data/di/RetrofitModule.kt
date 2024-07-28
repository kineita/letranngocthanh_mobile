package com.letranngocthanh.data.di

import com.google.gson.GsonBuilder
import com.letranngocthanh.data.source.user.remote.UserService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

val productionRetrofitModule = module {
    single {
        // OkHttpClient
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    single {
        // Gson
        GsonBuilder()
            .setLenient()
            .create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Use default URL
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    // Define your API service interface
    single {
        get<Retrofit>().create(UserService::class.java)
    }
}

// If we need setup dev environment
//private const val DEV_URL = "https://api.github.com/"
val devRetrofitModule = module {
//    single {
//        Retrofit.Builder()
//            .baseUrl(DEV_URL) // Use default URL
//            .client(get())
//            .addConverterFactory(GsonConverterFactory.create(get()))
//            .build()
//    }
}