package com.example.core.domain

import com.example.core.data.repository.CategoriesRepository
import javax.inject.Inject


class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {

    fun getCategories() = categoriesRepository.getCategories()
}