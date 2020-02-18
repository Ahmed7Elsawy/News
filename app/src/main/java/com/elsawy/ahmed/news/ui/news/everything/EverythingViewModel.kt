package com.elsawy.ahmed.news.ui.news.everything

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.Repository.NewsRepositoryImpl

class EverythingViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var everyNews: LiveData<NewsResponse>
    private var newsRepository: NewsRepositoryImpl = NewsRepositoryImpl(getApplication())


    fun getEveryNews(query: String, sortBy: String, uploadDate: String) {
        everyNews = newsRepository.getEveryNews(query, sortBy,uploadDate)
    }

}
