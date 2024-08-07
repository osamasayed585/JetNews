package com.droidos.model.response

import com.droidos.model.beans.NetworkArticle

class ArticlesResponse(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<NetworkArticle>,
)
