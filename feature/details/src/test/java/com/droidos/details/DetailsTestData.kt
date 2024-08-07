package com.droidos.details

import com.droidos.details.state.DetailsUiState
import com.droidos.model.beans.ArticleUIModel

const val ERROR_MESSAGE = "Error message"
const val SPECIFIC_TITLE = "the specific title"


val defaultDetailArticleUi = ArticleUIModel(
    title = "Hello this is the default title",
    image = "https://images.unsplash.com/photo-1542281286-9e0a16bb7366",
    description = "default description",
    publishedAt = "12/12/2024",
    sourceName = "CNN"
)

val specificTitleArticleUi = ArticleUIModel(
    title = SPECIFIC_TITLE,
    image = "https://images.unsplash.com/photo-1542281286-9e0a16bb7366",
    description = "default description",
    publishedAt = "12/12/2024",
    sourceName = "CNN"
)

val dummySuccess_DetailsState = DetailsUiState(
    isLoading = false,
    errorMessage = null,
    title = defaultDetailArticleUi.title,
    image = defaultDetailArticleUi.image,
    description = defaultDetailArticleUi.description,
    publishedAt = defaultDetailArticleUi.publishedAt,
    sourceName = defaultDetailArticleUi.sourceName
)


val dummySuccess_SpecificTitleDetail = DetailsUiState(
    isLoading = false,
    errorMessage = null,
    title = specificTitleArticleUi.title,
    image = specificTitleArticleUi.image,
    description = specificTitleArticleUi.description,
    publishedAt = specificTitleArticleUi.publishedAt,
    sourceName = specificTitleArticleUi.sourceName
)

val dummyError_DetailsState = DetailsUiState(
    isLoading = false,
    errorMessage = ERROR_MESSAGE,
    title = defaultDetailArticleUi.title,
    image = defaultDetailArticleUi.image,
    description = defaultDetailArticleUi.description,
    publishedAt = defaultDetailArticleUi.publishedAt,
    sourceName = defaultDetailArticleUi.sourceName
)
