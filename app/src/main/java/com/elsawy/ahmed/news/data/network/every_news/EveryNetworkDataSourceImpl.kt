package com.elsawy.ahmed.news.data.network.every_news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.internal.NoConnectivityException

class EveryNetworkDataSourceImpl (private val newsAPIService : NewsAPIService):
    EveryNewsNetworkDataSource {

    private val _downloadedEveryNews = MutableLiveData<NewsResponse>()

    override val downloadedEveryNews: MutableLiveData<NewsResponse>
        get() = _downloadedEveryNews


    override suspend fun fetchEveryNews(query : String, sortBy: String, uploadDate: String) {
        try {
            val fetchedTopNews = newsAPIService.getEveryNewsRequestAsync(query,sortBy,uploadDate).await()
            _downloadedEveryNews.postValue(fetchedTopNews)
        } catch (ex: NoConnectivityException) {
            Log.e("Connectivity", "no internet connection", ex)
        }
    }

}