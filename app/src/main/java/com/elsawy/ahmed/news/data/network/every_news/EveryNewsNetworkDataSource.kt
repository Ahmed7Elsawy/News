package com.elsawy.ahmed.news.data.network.every_news

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse

interface EveryNewsNetworkDataSource {

    val downloadedEveryNews : LiveData<NewsResponse>

    suspend fun fetchEveryNews(query : String, sortBy: String, uploadDate: String)
}