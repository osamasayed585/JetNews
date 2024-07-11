package com.droidos.home.data.model.response

import com.droidos.home.data.model.beans.Article

class ArticlesResponse(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<Article>,
)
