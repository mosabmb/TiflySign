package com.example.core.data.di

import com.example.core.data.repository.CategoriesRepository
import com.example.core.data.repository.OfflineCategoriesRepository
import com.example.core.data.repository.OfflineFirstUserDataRepository
import com.example.core.data.repository.OfflineSignsRepository
import com.example.core.data.repository.SignsRepository
import com.example.core.data.repository.UserDataRepository
import com.example.core.data.util.ConnectivityManagerNetworkMonitor
import com.example.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {


    @Binds
    fun bindsCategoriesRepository(
        categoriesRepository: OfflineCategoriesRepository
    ): CategoriesRepository

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository

    @Binds
    fun bindsSignsRepository(
        signsRepository: OfflineSignsRepository
    ): SignsRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor




}