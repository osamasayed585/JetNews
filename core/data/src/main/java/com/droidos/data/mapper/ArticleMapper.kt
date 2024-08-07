package com.droidos.data.mapper

import com.droidos.model.beans.ArticleUIModel
import com.droidos.model.beans.NetworkArticle

fun NetworkArticle.asExternalUiModel() = ArticleUIModel(
    title = title,
    image = urlToImage ?: "",
    description = description,
    publishedAt = publishedAt,
    sourceName = source.name,
)