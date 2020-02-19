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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(article: Article)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertAll(articles: List<Article>)

    @Query("DELETE FROM article_table")
    suspend fun deleteAll()
}