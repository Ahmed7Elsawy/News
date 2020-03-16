package com.elsawy.ahmed.news.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elsawy.ahmed.news.data.db.entity.Article

@Dao
interface ArticleDao {

    @Query("SELECT * from article_table")
    fun getAllArticles(): LiveData<List<Article>>

    @Query("SELECT count(*) from article_table")
    fun getArticlesCount(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: List<Article>)

    @Query("DELETE FROM article_table")
    suspend fun deleteAll()
}