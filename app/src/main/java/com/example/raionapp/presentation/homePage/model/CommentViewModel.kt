package com.example.raionapp.presentation.homePage.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionapp.firestore.CommentCollection
import com.example.raionapp.firestore.ProfileCollection
import com.example.raionapp.firestore.ThreadCollection
import com.example.raionapp.firestore.model.CommentDataClass
import com.example.raionapp.firestore.model.ProfileDataClass
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommentViewModel : ViewModel() {
    private val db: FirebaseFirestore = Firebase.firestore

    // Menggunakan MutableStateFlow untuk menyimpan list Triple:
    // (documentId, comment.commentId, CommentDataClass)
    private val _commentsState = MutableStateFlow<List<Triple<String, String, CommentDataClass>>>(emptyList())
    val commentState: StateFlow<List<Triple<String, String, CommentDataClass>>> = _commentsState.asStateFlow()

    fun loadComments(threadId: String) {
        db.collection("thread")
            .document(threadId)
            .collection("comment")
            .orderBy("timeCreated", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    Log.e("CommentViewModel", "Error fetching comments: ${error.message}")
                    _commentsState.value = emptyList()
                    return@addSnapshotListener
                }
                _commentsState.value = querySnapshot?.documents?.mapNotNull { document ->
                    document.toObject(CommentDataClass::class.java)?.let { comment ->
                        // Membuat Triple: (document.id, comment.commentId, comment)
                        Triple(document.id, comment.commentId, comment)
                    }
                } ?: emptyList()
            }
    }

    /**
     * Mengirim komentar ke subkoleksi "comment" di dalam dokumen thread.
     *
     * @param authorProfile Data profil user yang mengirim comment.
     * @param commentContent Isi komentar.
     * @param threadId ID thread tempat komentar ditambahkan.
     */
    fun sendComment(
        authorProfile: ProfileDataClass?,
        commentContent: String,
        threadId: String,
        imageUrl: String?
    ) {
        viewModelScope.launch {
            val answerCount = authorProfile?.numberOfAnswer ?: 0

            // Buat objek CommentDataClass; field numberOfLike diatur ke 0,
            // timeCreated akan diisi oleh server (FieldValue.serverTimestamp() pada proses penambahan)
            val comment = CommentDataClass(
                threadId = threadId,
                fullname = authorProfile?.fullname.orEmpty(),
                username = authorProfile?.username.orEmpty(),
                profilePicture = authorProfile?.profilePicture,
                text = commentContent,
                numberOfLike = 0,
                imageUrl = imageUrl
                // timeCreated akan diisi melalui FieldValue.serverTimestamp() di CommentCollection.addCommentToThread
            )

            // Tambahkan komentar ke subkoleksi "comment" dari thread dengan ID threadId
            CommentCollection().addCommentToThread(threadId, comment)

            // Update jumlah answer di profil user (misalnya, jumlah jawaban yang dikirim)
            val updateNumberOfAnswer = mapOf("numberOfAnswer" to (answerCount + 1))
            ProfileCollection().updateProfileInFirestore(
                authorProfile?.userId.toString(),
                updateNumberOfAnswer
            )

            // Update jumlah komentar pada thread dengan increment atomik
            ThreadCollection().updateThreadFirestore(
                threadId,
                mapOf("numberOfComment" to FieldValue.increment(1))
            )
        }
    }

    // Fungsi untuk menambah like pada komentar
    fun increaseCommentLike(threadId: String, commentId: String) {
        viewModelScope.launch {
            try {
                CommentCollection().updateComment(
                    threadId = threadId,
                    commentId = commentId,
                    updateData = mapOf(
                        "numberOfLike" to FieldValue.increment(1)
                    )
                )
            } catch (e: Exception) {
                Log.e("CommentViewModel", "threadId: $threadId, commentId: $commentId, error: ${e.message}")
            }
        }
    }

    // Fungsi untuk mengurangi like pada komentar
    fun decreaseCommentLike(threadId: String, commentId: String) {
        viewModelScope.launch {
            try {
                CommentCollection().updateComment(
                    threadId = threadId,
                    commentId = commentId,
                    updateData = mapOf(
                        "numberOfLike" to FieldValue.increment(-1)
                    )
                )
            } catch (e: Exception) {
                Log.e("CommentViewModel", "threadId: $threadId, commentId: $commentId, error: ${e.message}")
            }
        }
    }
}
