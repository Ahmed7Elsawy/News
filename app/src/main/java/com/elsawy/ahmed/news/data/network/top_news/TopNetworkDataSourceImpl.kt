package com.elsawy.ahmed.news.data.network.top_news

import android.util.Log
import com.elsawy.ahmed.news.data.db.entity.Article
import com.elsawy.ahmed.news.data.db.entity.NewsResponse
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.internal.NoConnectivityException

class TopNetworkDataSourceImpl (private val newsAPIService : NewsAPIService):
    TopNewsNetworkDataSource {

    override suspend fun fetchTopNews(country: String, category: String): List<Article> {
        val fetchedTopNews: NewsResponse
        return try {
            fetchedTopNews = newsAPIService.getTopNewsRequestAsync(country, category).await()
            fetchedTopNews.articles
        } catch (ex: NoConnectivityException) {
            Log.e("Connectivity", "no internet connection", ex)
            emptyList()
        }
    }

}