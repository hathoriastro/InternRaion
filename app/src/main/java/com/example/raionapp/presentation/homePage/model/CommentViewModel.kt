package com.example.raionapp.presentation.homePage.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raionapp.firestore.model.CommentDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CommentViewModel : ViewModel() {
    private val db: FirebaseFirestore = Firebase.firestore

    private val _commentsState = MutableLiveData<List<Triple<String, String, CommentDataClass>>>()
    val commentState: LiveData<List<Triple<String, String, CommentDataClass>>> = _commentsState

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
                        Triple(document.id, comment.commentId, comment)
                    }
                } ?: emptyList()
            }
    }


}
