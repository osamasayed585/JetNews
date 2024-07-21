package com.droidos.home.data.model.beans

data class Article(
    val id: Long = System.currentTimeMillis(),
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
)