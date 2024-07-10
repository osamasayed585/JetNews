package com.droidos.jetnews.di


import com.droidos.jetnews.units.networkMonitor.ConnectivityManagerNetworkMonitor
import com.droidos.jetnews.units.networkMonitor.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}