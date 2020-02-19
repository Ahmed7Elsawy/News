package com.elsawy.ahmed.news.data.repository

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.entity.NewsResponse

interface TopNewsRepository {
    fun getTopNews(): LiveData<NewsResponse >
}