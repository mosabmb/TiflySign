package com.example.core.data.repository

import com.example.core.data.model.Response.Failure
import com.example.core.model.data.Category
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class OfflineCategoriesRepository @Inject constructor(
    private val db : Firebase
) : CategoriesRepository {

    override fun getCategories(): Flow<List<Category>> = callbackFlow {
        val snapshotListener = db.firestore.collection("Categories").orderBy("id").addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val categories = snapshot.toObjects(Category::class.java)
                categories
            } else {
                Failure(e)
            }
            trySend(response as List<Category>)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

}