package com.elsawy.ahmed.news.data.network.internet_connection

import okhttp3.Interceptor

interface ConnectivityInterceptor : Interceptor {
    fun isOnline() : Boolean
}