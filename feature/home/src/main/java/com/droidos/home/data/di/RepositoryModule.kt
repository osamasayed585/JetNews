package com.droidos.home.data.di


import com.droidos.common.di.DispatcherProvider
import com.droidos.datastore.LocalDataStore
import com.droidos.home.data.remote.ArticlesService
import com.droidos.home.data.repository.ArticlesRepositoryImp
import com.droidos.home.domain.repository.ArticlesRepository
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
        dispatcherProvider: DispatcherProvider
    ): ArticlesRepository {
        return ArticlesRepositoryImp(
            apiService = articlesService,
            preferences = preferences,
            dispatcherProvider = dispatcherProvider
        )
    }

}