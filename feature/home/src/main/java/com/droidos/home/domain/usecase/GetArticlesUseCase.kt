package com.droidos.home.domain.usecase

import com.droidos.home.domain.repository.ArticlesRepository

class GetArticlesUseCase(
    private val articlesRepository: ArticlesRepository,
) {

    operator fun invoke(query: String) = articlesRepository.requestArticles(query)

}