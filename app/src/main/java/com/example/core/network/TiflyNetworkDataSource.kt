package com.example.core.network

import com.example.core.network.model.NetworkCategory
import com.example.core.network.model.NetworkSign

interface TiflyNetworkDataSource {

    suspend fun getSigns(ids: List<String>? = null): List<NetworkSign>
    suspend fun getCategories(ids: List<String>? = null): List<NetworkCategory>

}
