package com.droidos.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val publishedAt: String,
    val sourceName: String
)

