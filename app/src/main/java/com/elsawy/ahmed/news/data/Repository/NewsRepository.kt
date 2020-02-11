package com.elsawy.ahmed.news.data.Repository

import androidx.lifecycle.LiveData
import com.elsawy.ahmed.news.data.Entity.NewsResponse

interface NewsRepository {

    fun getTopNews(): LiveData<out NewsResponse >

    fun getEveryNews(): LiveData<out NewsResponse >

}