package com.elsawy.ahmed.news.data.Repository

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse

interface NewsRepository {

    fun getTopNews(): LiveData<NewsResponse >

    fun getEveryNews(query :String, sortBy: String): LiveData<NewsResponse >

    fun getDateFilterNews(query :String, sortBy: String, uploadDate: String): LiveData<NewsResponse >

}