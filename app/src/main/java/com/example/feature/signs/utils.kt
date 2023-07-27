package com.example.feature.signs

import androidx.lifecycle.SavedStateHandle
import javax.inject.Qualifier


const val CATEGORY_ID = "categoryId"


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CategoryId


val SavedStateHandle.categoryId: String
    get() = get<String>(CATEGORY_ID).toString()
