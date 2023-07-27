package com.example.feature.signs

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)

object SignsModule {

    @Provides
    @CategoryId
    @ViewModelScoped
    fun provideCategoryId(savedStateHandle: SavedStateHandle): String =
        savedStateHandle.categoryId

}