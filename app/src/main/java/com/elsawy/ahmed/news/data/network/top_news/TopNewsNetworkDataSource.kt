package com.elsawy.ahmed.news.data.network.top_news

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.entity.NewsResponse

interface TopNewsNetworkDataSource {

    val downloadedTopNews : LiveData<NewsResponse>

    suspend fun fetchTopNews(country: String,category: String)
}