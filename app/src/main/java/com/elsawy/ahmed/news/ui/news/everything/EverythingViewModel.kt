package com.elsawy.ahmed.news.ui.news.everything

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.Repository.NewsRepositoryImpl

class EverythingViewModel : ViewModel() {
    lateinit var everyNews: LiveData<NewsResponse>

    lateinit var newsRepository: NewsRepositoryImpl

    fun initNewsRepository(context: Context) {
        newsRepository = NewsRepositoryImpl(context)
    }

    fun getEveryNews(query: String, sortBy: String) {
        everyNews = newsRepository.getEveryNews(query, sortBy)
    }

    fun getDateFilterNews(query: String, sortBy: String, uploadDate: String) {
        everyNews = newsRepository.getDateFilterNews(query, sortBy,uploadDate)
        Log.i("datefilter",uploadDate)
    }

}
