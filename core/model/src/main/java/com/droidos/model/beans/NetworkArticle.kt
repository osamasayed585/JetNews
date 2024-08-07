package com.droidos.model.beans

data class NetworkArticle(
    val id: Long = System.currentTimeMillis(),
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)