package com.droidos.domain.useCases

import com.droidos.domain.repository.ArticleDetailsRepository
import com.droidos.model.beans.ArticleUIModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetArticleDetailsUseCase @Inject constructor(
    private val repository: ArticleDetailsRepository,
) {
    suspend operator fun invoke(query: String) = repository.requestArticleDetails(query)
}