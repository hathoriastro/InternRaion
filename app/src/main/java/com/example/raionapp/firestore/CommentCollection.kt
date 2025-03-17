package com.example.raionapp.firestore

import android.util.Log
import com.example.raionapp.firestore.model.CommentDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class CommentCollection {
    val db = Firebase.firestore

    suspend fun addCommentToThread(threadId: String, comment: CommentDataClass) {
        val commentHashMap = hashMapOf(
            "threadId" to threadId,
            "fullname" to comment.fullname,
            "username" to comment.username,
            "text" to comment.text,
            "numberOfLike" to comment.numberOfLike,
            "profilePicture" to comment.profilePicture,
            "isLiked" to false,
            "timeCreated" to FieldValue.serverTimestamp()
        )
        val docomentRef = db.collection("thread")
            .document(threadId)
            .collection("comment")
            .add(commentHashMap)
            .await()

        val getCommentId = docomentRef.id

        db.collection("thread")
            .document(threadId)
            .collection("comment")
            .document(getCommentId)
            .update("commentId", getCommentId)
            .addOnCompleteListener {
                Log.d("CommentCollection", "threadId: $threadId, commentId: $getCommentId")
            }.addOnFailureListener { e ->
                Log.e("CommentCollection", "Gagal menambahkan comment ", e)
            }.await()
    }

    suspend fun updateComment(
        threadId: String,
        commentId: String,
        updateData: Map<String, Any>
    ) {
        db.collection("thread")
            .document(threadId)
            .collection("comment")
            .document(commentId)
            .update(updateData)
            .addOnSuccessListener { document ->
                Log.d("CommentCollection", "Dokumen berhasil diupdate dengan ID: $commentId")
            }.addOnFailureListener { e ->
                Log.w("CommentCollection", "Error updating document", e)
            }.await()
    }
}