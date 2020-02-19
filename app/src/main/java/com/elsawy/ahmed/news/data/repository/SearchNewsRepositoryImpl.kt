package com.elsawy.ahmed.news.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.entity.NewsResponse
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.data.network.every_news.EveryNetworkDataSourceImpl
import com.elsawy.ahmed.news.data.network.internet_connection.ConnectivityInterceptorImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchNewsRepositoryImpl (private val context : Context): SearchNewsRepository {
    private lateinit var articles: LiveData<NewsResponse>

    override fun getEveryNews(
        query: String,
        sortBy: String,
        uploadDate: String
    ): LiveData<NewsResponse> {
        val newsAPIService = NewsAPIService(
            ConnectivityInterceptorImpl(
                this.context
            )
        )

        val newsNetworkDataSource =
            EveryNetworkDataSourceImpl(
                newsAPIService
            )

        articles = newsNetworkDataSource.downloadedEveryNews

        CoroutineScope(Dispatchers.IO ).launch {
            newsNetworkDataSource.fetchEveryNews(query, sortBy,uploadDate)
        }

        return articles
    }

}