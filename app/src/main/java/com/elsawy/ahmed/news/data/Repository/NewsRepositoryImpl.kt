package com.elsawy.ahmed.news.data.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.network.internet_connection.ConnectivityInterceptorImpl
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.data.network.every_news.EveryNetworkDataSourceImpl
import com.elsawy.ahmed.news.data.network.top_news.TopNetworkDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsRepositoryImpl(private val context : Context) : NewsRepository {

    private lateinit var articles: LiveData<NewsResponse>
    override  fun getTopNews(): LiveData<out NewsResponse> {

        val newsAPIService = NewsAPIService(
            ConnectivityInterceptorImpl(
                this.context
            )
        )
        val newsNetworkDataSource =
            TopNetworkDataSourceImpl(
                newsAPIService
            )

        articles = newsNetworkDataSource.downloadedTopNews

        GlobalScope.launch(Dispatchers.Main) {
            newsNetworkDataSource.fetchTopNews()
        }

        return articles
    }

    override fun getEveryNews(): LiveData<out NewsResponse> {

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

        GlobalScope.launch(Dispatchers.Main) {
            newsNetworkDataSource.fetchEveryNews()
        }

        return articles
    }

}