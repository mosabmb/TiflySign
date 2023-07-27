package com.example.core.domain

import com.example.core.data.repository.SignsRepository
import javax.inject.Inject

class GetSignsUseCase @Inject constructor(
    private val signsRepository: SignsRepository,
) {

    fun getSigns() = signsRepository.getSigns()

    fun getSignById(signId: String) = signsRepository.getSignById(signId = signId)

    fun getSignByCategory(categoryName: String) = signsRepository.getSignByCategory(categoryName = categoryName)

}