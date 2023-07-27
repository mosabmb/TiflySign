package com.example.core.data.di


import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideCollection() = Firebase

/*
    @Provides
    fun provideOfflineSignRepository(
        signsRef: CollectionReference
    ): SignsRepository = OfflineSignsRepository(signsRef)*/


}