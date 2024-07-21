package com.droidos.home.domain.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class ArticleUIModel(
    val title: String,
    val image: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
): Parcelable
