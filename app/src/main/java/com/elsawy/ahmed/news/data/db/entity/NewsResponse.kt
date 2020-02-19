package com.elsawy.ahmed.news.data.db.entity


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)