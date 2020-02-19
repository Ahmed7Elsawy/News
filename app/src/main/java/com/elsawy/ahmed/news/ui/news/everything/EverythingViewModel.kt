package com.elsawy.ahmed.news.ui.news.everything

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.entity.NewsResponse
import com.elsawy.ahmed.news.data.repository.SearchNewsRepository
import com.elsawy.ahmed.news.data.repository.SearchNewsRepositoryImpl

class EverythingViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var everyNews: LiveData<NewsResponse>
    private var searchNewsRepository: SearchNewsRepository = SearchNewsRepositoryImpl(getApplication())


    fun getEveryNews(query: String, sortBy: String, uploadDate: String) {
        everyNews = searchNewsRepository.getEveryNews(query, sortBy,uploadDate)
    }

}
