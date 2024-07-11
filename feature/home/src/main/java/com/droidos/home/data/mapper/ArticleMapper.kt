package com.droidos.home.data.mapper

import com.droidos.home.data.model.beans.Article
import com.droidos.home.domain.model.ArticleUIModel

fun Article.toArticleUIModel() = ArticleUIModel(
    title = title,
    image = urlToImage,
    description = description,
    publishedAt = publishedAt,
    sourceName = source.name
)