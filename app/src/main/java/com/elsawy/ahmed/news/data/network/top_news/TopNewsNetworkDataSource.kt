package com.elsawy.ahmed.news.data.network.top_news

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.entity.Article
import com.elsawy.ahmed.news.data.db.entity.NewsResponse

interface TopNewsNetworkDataSource {
    suspend fun fetchTopNews(country: String,category: String):List<Article>
}