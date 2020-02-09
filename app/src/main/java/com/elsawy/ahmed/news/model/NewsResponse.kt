package com.elsawy.ahmed.news.model


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)