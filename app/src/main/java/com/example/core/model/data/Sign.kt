package com.example.core.model.data

data class Sign(
    val id: String? = null,
    val mot_fr: String? = null,
    val mot_ar: String? = null,
    val category: String? = null,
    val gif: String? = null
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$mot_fr$mot_fr",
            "${mot_fr?.first()}",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}