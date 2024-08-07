package com.droidos.details.state

import com.droidos.common.base.ScreenState

data class DetailsUiState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val title: String = "",
    val image: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val sourceName: String = "",
) : ScreenState