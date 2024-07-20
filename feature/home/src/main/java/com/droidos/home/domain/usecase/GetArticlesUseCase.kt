package com.droidos.home.domain.usecase

import androidx.paging.PagingData
import androidx.paging.filter
import com.droidos.common.utils.Constants.AL_JAZEERA_NET
import com.droidos.home.data.model.beans.Article
import com.droidos.home.domain.repository.ArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
) {

    /**
     * Fetches a flow of paged articles based on the provided query.
     *
     * This function retrieves articles from the `articlesRepository` and filters out articles
     * from "Al Jazeera Net" and those without an image URL.
     *
     * @param query The search query for articles.
     * @return A flow of PagingData containing the filtered articles.
     */
    operator fun invoke(query: String): Flow<PagingData<Article>> {
        return articlesRepository.requestArticles(query).map {
                it.filter { article ->
                    article.source.name != AL_JAZEERA_NET && article.urlToImage != null
                }
            }
    }

}