package com.droidos.data.di


import com.droidos.common.di.DispatcherProvider
import com.droidos.data.remote.ArticlesService
import com.droidos.data.repository.articles.ArticlesRepositoryImp
import com.droidos.data.repository.details.ArticleDetailsRepositoryImp
import com.droidos.datastore.LocalDataStore
import com.droidos.domain.repository.ArticleDetailsRepository
import com.droidos.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideArticlesRepository(
        articlesService: ArticlesService,
        preferences: LocalDataStore,
        dispatcherProvider: DispatcherProvider,
    ): ArticlesRepository {
        return ArticlesRepositoryImp(
            apiService = articlesService,
            preferences = preferences,
            dispatcherProvider = dispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun provideArticleDetailsRepository(
        articlesService: ArticlesService,
        preferences: LocalDataStore,
        dispatcherProvider: DispatcherProvider,
    ): ArticleDetailsRepository {
        return ArticleDetailsRepositoryImp(
            apiService = articlesService,
            preferences = preferences,
            dispatcherProvider = dispatcherProvider
        )
    }

}