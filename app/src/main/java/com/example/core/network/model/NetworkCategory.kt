package com.example.core.network.model

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCategory(
    val id: String,
    val name: String,
    val description: String,
    val wordsCount: Int,
    val icon: ImageVector,
)