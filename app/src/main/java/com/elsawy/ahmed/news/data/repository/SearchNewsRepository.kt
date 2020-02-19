package com.elsawy.ahmed.news.data.repository

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.entity.NewsResponse

interface SearchNewsRepository {
    fun getEveryNews(query :String, sortBy: String, uploadDate: String): LiveData<NewsResponse>
}