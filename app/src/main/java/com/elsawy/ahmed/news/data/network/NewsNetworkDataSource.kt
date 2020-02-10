package com.elsawy.ahmed.news.data.network

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse

interface NewsNetworkDataSource {

    val downloadedTopNews : LiveData<NewsResponse>

    suspend fun fetchTopNews()
}