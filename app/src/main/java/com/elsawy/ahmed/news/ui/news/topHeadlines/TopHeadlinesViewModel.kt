package com.elsawy.ahmed.news.ui.news.topHeadlines

import android.app.Application
import androidx.lifecycle.*
import com.elsawy.ahmed.news.data.repository.TopNewsRepositoryImpl
import com.elsawy.ahmed.news.data.db.ArticleDao
import com.elsawy.ahmed.news.data.db.ArticleRoomDatabase
import com.elsawy.ahmed.news.data.db.entity.Article
import com.elsawy.ahmed.news.data.network.internet_connection.ConnectivityInterceptor
import com.elsawy.ahmed.news.data.network.internet_connection.ConnectivityInterceptorImpl
import com.elsawy.ahmed.news.data.repository.TopNewsRepository
import kotlinx.coroutines.launch

class TopHeadlinesViewModel(application: Application) : AndroidViewModel(application) {

    var topNews: LiveData<List<Article>>

    private val topNewsRepository: TopNewsRepository
    private val internetConnectivity: ConnectivityInterceptor

    init {
        val articleDao: ArticleDao = ArticleRoomDatabase(getApplication()).articleDao()
        internetConnectivity = ConnectivityInterceptorImpl(getApplication())
        topNewsRepository = TopNewsRepositoryImpl(getApplication(), articleDao)

        topNews = topNewsRepository.getTopArticlesFromDatabase()
        updateTopArticlesFromServer()
    }

    fun updateTopArticlesFromServer() {
        viewModelScope.launch {
            if (internetConnectivity.isOnline())
                topNewsRepository.getTopArticlesFromServer()
        }
    }

}
