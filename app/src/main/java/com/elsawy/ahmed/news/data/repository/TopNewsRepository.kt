package com.elsawy.ahmed.news.data.repository

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.db.entity.Article

interface TopNewsRepository {
    suspend fun getTopArticlesFromServer()
    fun getTopArticlesFromDatabase(): LiveData<List<Article>>
}