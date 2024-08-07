package com.droidos.domain.useCases

import androidx.paging.PagingData
import androidx.paging.filter
import com.droidos.common.utils.Constants.AL_JAZEERA_NET
import com.droidos.domain.repository.ArticlesRepository
import com.droidos.model.beans.ArticleUIModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetArticlesUseCase @Inject constructor(
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
    operator fun invoke(query: String): Flow<PagingData<ArticleUIModel>> {
        return articlesRepository.requestArticles(query).map {
                it.filter { article -> article.sourceName != AL_JAZEERA_NET }
            }
    }

}