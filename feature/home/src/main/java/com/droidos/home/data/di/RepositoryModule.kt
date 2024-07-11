package com.droidos.home.data.di


import com.droidos.datastore.LocalDataStore
import com.droidos.home.data.remote.ArticlesService
import com.droidos.home.data.repository.ArticlesRepositoryImp
import com.droidos.home.domain.repository.ArticlesRepository
import com.droidos.common.di.DefaultDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        moviesService: ArticlesService,
        preferences: LocalDataStore,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): ArticlesRepository {
        return ArticlesRepositoryImp(
            apiService = moviesService,
            preferences = preferences,
            defaultDispatcher = defaultDispatcher
        )
    }

}