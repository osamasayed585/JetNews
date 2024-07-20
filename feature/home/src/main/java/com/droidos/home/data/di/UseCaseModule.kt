package com.droidos.home.data.di

import com.droidos.home.domain.repository.ArticlesRepository
import com.droidos.home.domain.usecase.GetArticlesUseCase
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

}