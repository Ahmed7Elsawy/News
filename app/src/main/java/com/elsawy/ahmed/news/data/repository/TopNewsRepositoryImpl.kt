package com.elsawy.ahmed.news.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.ArticleDao
import com.elsawy.ahmed.news.data.db.entity.NewsResponse
import com.elsawy.ahmed.news.data.network.internet_connection.ConnectivityInterceptorImpl
import com.elsawy.ahmed.news.data.network.NewsAPIService
import com.elsawy.ahmed.news.data.network.top_news.TopNetworkDataSourceImpl
import com.elsawy.ahmed.news.data.provider.CategoryProviderImpl
import com.elsawy.ahmed.news.data.provider.CountryProviderImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TopNewsRepositoryImpl(private val context : Context, private val articleDao: ArticleDao) : TopNewsRepository {

    private lateinit var articles: LiveData<NewsResponse>
    override  fun getTopNews(): LiveData<NewsResponse> {

        val newsAPIService = NewsAPIService(
            ConnectivityInterceptorImpl(
                this.context
            )
        )
        val newsNetworkDataSource =
            TopNetworkDataSourceImpl(
                newsAPIService
            )

        articles = newsNetworkDataSource.downloadedTopNews

        GlobalScope.launch(Dispatchers.IO) {
            newsNetworkDataSource.fetchTopNews(getPreferredCountry(),getPreferredCategory())
            insertNewArticlesDB()
        }

        return articles
    }

    private fun getPreferredCountry() : String{
        val countryProviderImpl = CountryProviderImpl(context)
        return countryProviderImpl.getPreferredCountryString()
    }

    private fun getPreferredCategory() : String{
        val categoryProviderImpl = CategoryProviderImpl(context)
        return categoryProviderImpl.getPreferredCategoryString()
    }

    private fun insertNewArticlesDB() {
//        for (article in articles.value?.articles!!)
//            articleDao.insert(article)
//        Log.i("article",articles.value?.articles!!.size.toString())
    }

}