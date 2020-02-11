package com.elsawy.ahmed.news.ui.news.everything

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.Repository.NewsRepositoryImpl

class EverythingViewModel : ViewModel() {
    lateinit var everyNews : LiveData<NewsResponse>

    fun getEvertNews(context: Context) {
        val newsRepository = NewsRepositoryImpl(context)

        everyNews = newsRepository.getEveryNews() as LiveData<NewsResponse>
    }
}
