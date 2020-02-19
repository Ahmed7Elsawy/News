package com.elsawy.ahmed.news.ui.news.topHeadlines

import android.app.Application
import androidx.lifecycle.*
import com.elsawy.ahmed.news.data.repository.TopNewsRepository
import com.elsawy.ahmed.news.data.db.entity.NewsResponse
import com.elsawy.ahmed.news.data.repository.TopNewsRepositoryImpl
import com.elsawy.ahmed.news.data.db.ArticleDao
import com.elsawy.ahmed.news.data.db.ArticleRoomDatabase

class TopHeadlinesViewModel(application: Application) : AndroidViewModel(application){

    var topNews : LiveData<NewsResponse>
    private val topNewsRepository: TopNewsRepository

    init {
        val articleDao: ArticleDao = ArticleRoomDatabase(getApplication()).articleDao()

        topNewsRepository = TopNewsRepositoryImpl(getApplication(),articleDao)
        topNews = topNewsRepository.getTopNews()



//        allArticles = repository.allArticles
    }

}
