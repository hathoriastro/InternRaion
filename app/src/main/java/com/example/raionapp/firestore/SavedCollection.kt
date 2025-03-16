package com.example.raionapp.firestore

import android.util.Log
import com.example.raionapp.firestore.model.CommentDataClass
import com.example.raionapp.firestore.model.ThreadDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class SavedCollection {
    private val db = Firebase.firestore

    /**
     * Menambahkan thread ke subkoleksi "savedQuestion" pada dokumen profil user.
     * @param userId ID profil user.
     * @param thread ThreadDataClass yang akan disimpan.
     */
    suspend fun addSavedQuestion(userId: String, thread: ThreadDataClass) {
        // Siapkan data thread yang ingin disimpan.
        val data = hashMapOf(
            "threadId" to thread.threadId,
            "userId" to thread.userId,
            "fullname" to thread.fullname,
            "username" to thread.username,
            "threadText" to thread.threadText,
            "authorProfilePicture" to thread.authorProfilePicture,
            "numberOfLike" to thread.numberOfLike,
            "numberOfComment" to thread.numberOfComment,
            "timeCreated" to (thread.timeCreated ?: FieldValue.serverTimestamp())
        )
        // Tambahkan dokumen ke subkoleksi "savedQuestion"
        val documentRef = db.collection("profile")
            .document(userId)
            .collection("savedQuestion")
            .add(data)
            .await()
        val generatedId = documentRef.id
        // Perbarui dokumen dengan menyimpan ID-nya sebagai savedQuestionId (opsional)
        db.collection("profile")
            .document(userId)
            .collection("savedQuestion")
            .document(generatedId)
            .update("savedQuestionId", generatedId)
            .addOnCompleteListener {
                Log.d("SavedCollection", "Saved question berhasil disimpan dengan ID: $generatedId")
            }
            .addOnFailureListener { e ->
                Log.w("SavedCollection", "Gagal menyimpan saved question", e)
            }
            .await()
    }

    /**
     * Menambahkan comment ke subkoleksi "savedAnswer" pada dokumen profil user.
     * @param userId ID profil user.
     * @param comment CommentDataClass yang akan disimpan.
     */
    suspend fun addSavedAnswer(userId: String, comment: CommentDataClass) {
        val data = hashMapOf(
            "commentId" to comment.commentId,
            "threadId" to comment.threadId,
            "fullname" to comment.fullname,
            "username" to comment.username,
            "profilePicture" to comment.profilePicture,
            "text" to comment.text,
            "numberOfLike" to comment.numberOfLike,
//            "share" to comment.share,
//            "save" to comment.save,
            "timeCreated" to (comment.timeCreated ?: FieldValue.serverTimestamp())
        )
        val documentRef = db.collection("profile")
            .document(userId)
            .collection("savedAnswer")
            .add(data)
            .await()
        val generatedId = documentRef.id
        db.collection("profile")
            .document(userId)
            .collection("savedAnswer")
            .document(generatedId)
            .update("savedAnswerId", generatedId)
            .addOnCompleteListener {
                Log.d("SavedCollection", "Saved answer berhasil disimpan dengan ID: $generatedId")
            }
            .addOnFailureListener { e ->
                Log.w("SavedCollection", "Gagal menyimpan saved answer", e)
            }
            .await()
    }
}