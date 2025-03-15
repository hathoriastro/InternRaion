package com.example.raionapp.Firestore

import android.util.Log
import com.example.raionapp.Firestore.Model.ThreadDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ThreadCollection {
    val db = Firebase.firestore

    suspend fun addThreadToFirestore(thread: ThreadDataClass) {
            val threadHashMap = hashMapOf(
                "userId" to thread.userId,
                "fullname" to thread.fullname,
                "username" to thread.username,
                "threadText" to thread.threadText,
                "authorProfilePicture" to thread.authorProfilePicture,
                "numberOfLike" to thread.numberOfLike,
                "numberOfComment" to thread.numberOfComment
            )
    //        Membuat document terlebih dahulu, lalu mengambil id nya untuk dijadikan threadId
            val documentId = db.collection("thread").add(threadHashMap).await()
            val getDocumentId = documentId.id

            db.collection("thread").document(getDocumentId)
                .update("threadId", getDocumentId)
                .addOnCompleteListener {
                    Log.d("ThreadCollection", "Dokumen berhasil disimpan dengan ID: $getDocumentId")
                }.addOnFailureListener { e ->
                    Log.w("ThreadCollection", "Gagal menambahkan thread ", e)
                }.await()
    }

    suspend fun updateLikeAndCommentThread(
        threadId: String,
        updateData: Map<String, Any>
    ) {
        db.collection("thread")
            .document(threadId)
            .update(updateData)
            .addOnSuccessListener { document ->
                Log.d("ThreadCollection", "Dokumen berhasil diupdate dengan ID: $threadId")
            }.addOnFailureListener { e ->
                Log.w("ThreadCollection", "Error updating document", e)
            }.await()
    }
}