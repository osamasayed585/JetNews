package com.droidos.data.di

import com.droidos.domain.repository.ArticleDetailsRepository
import com.droidos.domain.repository.ArticlesRepository
import com.droidos.domain.useCases.GetArticleDetailsUseCase
import com.droidos.domain.useCases.GetArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideArticlesUseCase(articlesRepository: ArticlesRepository): GetArticlesUseCase {
        return GetArticlesUseCase(articlesRepository)
    }

    @Provides
    @Singleton
    fun provideArticleDetailsUseCase(articleDetailsRepository: ArticleDetailsRepository): GetArticleDetailsUseCase {
        return GetArticleDetailsUseCase(articleDetailsRepository)
    }

}