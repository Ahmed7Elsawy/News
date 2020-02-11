package com.elsawy.ahmed.news.ui.news.topHeadlines

import android.content.Context
import androidx.lifecycle.*
import com.elsawy.ahmed.news.data.Entity.NewsResponse
import com.elsawy.ahmed.news.data.Repository.NewsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TopHeadlinesViewModel : ViewModel(){

    lateinit var topNews : LiveData<NewsResponse>

    fun getTopNews(context: Context) {
        val newsRepository = NewsRepositoryImpl(context)

         topNews = newsRepository.getTopNews() as LiveData<NewsResponse>
    }

}
