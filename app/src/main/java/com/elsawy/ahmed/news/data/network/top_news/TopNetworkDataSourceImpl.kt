package com.elsawy.ahmed.news.data.network.top_news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.internal.NoConnectivityException

class TopNetworkDataSourceImpl (private val newsAPIService : NewsAPIService):
    TopNewsNetworkDataSource {

    private val _downloadedTopNews = MutableLiveData<NewsResponse>()

    override val downloadedTopNews: LiveData<NewsResponse>
        get() = _downloadedTopNews

    override suspend fun fetchTopNews(country: String,category: String) {
        try {
            val fetchedTopNews = newsAPIService.getTopNewsRequestAsync(country,category).await()
            _downloadedTopNews.postValue(fetchedTopNews)
        }catch (ex : NoConnectivityException){
            Log.e("Connectivity","no internet connection", ex)
        }
    }

}