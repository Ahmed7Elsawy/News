package com.elsawy.ahmed.news.data.network.every_news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.internal.NoConnectivityException

class EveryNetworkDataSourceImpl (private val newsAPIService : NewsAPIService):
    EveryNewsNetworkDataSource {

    private val _downloadedEveryNews = MutableLiveData<NewsResponse>()

    override val downloadedEveryNews: LiveData<NewsResponse>
        get() = _downloadedEveryNews

    override suspend fun fetchEveryNews() {
        try {
            val fetchedTopNews = newsAPIService.getEveryNewsRequest().await()
            _downloadedEveryNews.postValue(fetchedTopNews)
        } catch (ex: NoConnectivityException) {
            Log.e("Connectivity", "no internet connection", ex)
        }
    }

}