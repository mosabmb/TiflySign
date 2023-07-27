package com.example.core.network.model

import kotlinx.serialization.Serializable

@Serializable
class NetworkSign(
    val id: String,
    val mot_fr: String,
    val mot_ar: String,
    val description: String,
)