package com.elsawy.ahmed.news.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.internal.NoConnectivityException

class NewsNetworkDataSourceImpl (private val newsAPIService : NewsAPIService): NewsNetworkDataSource {

    private val _downloadedTopNews = MutableLiveData<NewsResponse>()

    override val downloadedTopNews: LiveData<NewsResponse>
        get() = _downloadedTopNews

    override suspend fun fetchTopNews() {
        try {
            val fetchedTopNews = newsAPIService.getTopNewsRequest().await()
            _downloadedTopNews.postValue(fetchedTopNews)
        }catch (ex : NoConnectivityException){
            Log.e("Connectivity","no internet connection", ex)
        }
    }
}