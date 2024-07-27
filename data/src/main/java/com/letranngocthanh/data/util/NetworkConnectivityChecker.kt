package com.letranngocthanh.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

interface NetworkConnectivityChecker {

    fun isInternetAvailable(): Boolean
}

class NetworkConnectivityCheckerImpl(
    private val context: Context
) : NetworkConnectivityChecker {

    override fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}