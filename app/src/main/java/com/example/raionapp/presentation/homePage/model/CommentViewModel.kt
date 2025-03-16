package com.example.raionapp.presentation.homePage.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raionapp.firestore.model.CommentDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class CommentViewModel: ViewModel() {
    private val db: FirebaseFirestore = Firebase.firestore

    private val _commentsState = MutableLiveData<List<Pair<String, CommentDataClass>>>()
    val commentState: LiveData<List<Pair<String, CommentDataClass>>> = _commentsState

    fun loadComments(threadId: String) {
        db.collection("thread")
            .document(threadId)
            .collection("comment")
            .orderBy("timeCreated", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, error ->
                _commentsState.value = querySnapshot?.documents?.mapNotNull { document ->
                    document.toObject(CommentDataClass::class.java)?.let { thread ->
                        Pair(document.id, thread)
                    }
                } ?: emptyList()
            }
    }
}