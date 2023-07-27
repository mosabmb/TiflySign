package com.example.core.data.repository

import com.example.core.model.data.Sign
import kotlinx.coroutines.flow.Flow


interface SignsRepository  {

    fun getSigns(): Flow<List<Sign>>
    fun getSignById(signId: String): Flow<List<Sign>>
    fun getSignByCategory(categoryName: String): Flow<List<Sign>>


}
