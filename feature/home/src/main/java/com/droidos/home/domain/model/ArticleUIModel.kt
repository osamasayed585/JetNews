package com.droidos.home.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class ArticleUIModel(
    val title: String,
    val image: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
)
