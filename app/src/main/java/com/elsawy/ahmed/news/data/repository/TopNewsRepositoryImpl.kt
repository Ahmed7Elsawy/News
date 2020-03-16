package com.elsawy.ahmed.news.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.ArticleDao
import com.elsawy.ahmed.news.data.db.entity.Article
import com.elsawy.ahmed.news.data.network.internet_connection.ConnectivityInterceptorImpl
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.data.network.top_news.TopNetworkDataSourceImpl
import com.elsawy.ahmed.news.data.provider.CategoryProviderImpl
import com.elsawy.ahmed.news.data.provider.CountryProviderImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
* this is repository get top articles from server and save it in database after remove old articles
* read the top articles from database
*/
class TopNewsRepositoryImpl(private val context : Context, private val articleDao: ArticleDao) : TopNewsRepository {

    override fun getTopArticlesFromDatabase() = articleDao.getAllArticles()

    // get top articles from server and save it in database after remove old articles
    override suspend fun getTopArticlesFromServer() {
        val newsAPIService = NewsAPIService(
            ConnectivityInterceptorImpl(
                this.context
            )
        )

        val newsNetworkDataSource =
            TopNetworkDataSourceImpl(
                newsAPIService
            )

        withContext(Dispatchers.IO) {
            val articles =
                newsNetworkDataSource.fetchTopNews(getPreferredCountry(), getPreferredCategory())
            deleteOldArticlesFromDatabase()
            insertNewArticlesToDatabase(articles)
        }

    }

    private suspend fun insertNewArticlesToDatabase(articles: List<Article>) {
        articleDao.insert(articles)
    }

    private suspend fun deleteOldArticlesFromDatabase() {
        articleDao.deleteAll()
    }

    // get country from shared preference settings
    private fun getPreferredCountry(): String {
        val countryProviderImpl = CountryProviderImpl(context)
        return countryProviderImpl.getPreferredCountryString()
    }

    // get category from shared preference settings
    private fun getPreferredCategory(): String {
        val categoryProviderImpl = CategoryProviderImpl(context)
        return categoryProviderImpl.getPreferredCategoryString()
    }

}