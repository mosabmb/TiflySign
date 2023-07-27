package com.example.core.data.repository

import com.example.core.model.data.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    fun getCategories(): Flow<List<Category>>

}