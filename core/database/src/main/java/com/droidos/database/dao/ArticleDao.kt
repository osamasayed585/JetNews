package com.droidos.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.droidos.database.model.ArticleEntity

interface ArticleDao {

    /**
     * Inserts [articleEntities] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreArticles(articleEntities: List<ArticleEntity>): List<Long>
}