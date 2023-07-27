package com.example.core.data.repository

import android.util.Log
import com.example.core.data.model.Response
import com.example.core.model.data.Sign
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class OfflineSignsRepository @Inject constructor(
    private val db : Firebase
) : SignsRepository {

    override fun getSigns(): Flow<List<Sign>> = callbackFlow {
        val snapshotListener = db.firestore.collection("Signs").orderBy("id").addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val signs = snapshot.toObjects(Sign::class.java)
                signs
            } else {
                Response.Failure(e)
            }
            trySend(response as List<Sign>)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getSignById(signId: String): Flow<List<Sign>> = callbackFlow {

        val snapshotListener = db.firestore
            .collection("Signs")
            .whereEqualTo("id",signId)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val signs = snapshot.toObjects(Sign::class.java)
                    signs
                } else {
                    Response.Failure(e)
                }
                trySend(response as List<Sign>)
        }
        awaitClose {
            snapshotListener.remove()
        }

    }

    override fun getSignByCategory(categoryName: String): Flow<List<Sign>> = callbackFlow {
        Log.d("Mabrouki",categoryName)
        val snapshotListener = db.firestore
            .collection("Signs")
            .whereEqualTo("category",categoryName)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val signs = snapshot.toObjects(Sign::class.java)
                    signs
                } else {
                    Response.Failure(e)
                }
                trySend(response as List<Sign>)
            }
        awaitClose {
            snapshotListener.remove()
        }

    }


}

