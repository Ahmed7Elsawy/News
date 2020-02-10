package com.elsawy.ahmed.news.data.Entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "Article")
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
//    @Embedded(prefix = "Source_")
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)
//{
//    @PrimaryKey(autoGenerate = true)
//    var Id : Int = 0
//}