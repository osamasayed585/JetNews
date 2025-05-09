package com.droidos.details.state

import com.droidos.common.base.ScreenState
import com.droidos.network.di.errorHandler.entities.ErrorEntity

data class DetailsUiState(
    val isLoading: Boolean = true,
    val errorEntity: ErrorEntity? = null,
    val title: String = "",
    val image: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val sourceName: String = "",
) : ScreenState