package com.example.raionapp.presentation.homePage.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raionapp.firestore.CommentCollection
import com.example.raionapp.firestore.ProfileCollection
import com.example.raionapp.firestore.ThreadCollection
import com.example.raionapp.firestore.model.CommentDataClass
import com.example.raionapp.firestore.model.ProfileDataClass
import com.example.raionapp.firestore.model.ThreadDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ThreadViewModel: ViewModel() {
    private val db: FirebaseFirestore = Firebase.firestore

    private val _threadsState = MutableLiveData<List<Pair<String, ThreadDataClass>>>()
    val threadsState: LiveData<List<Pair<String, ThreadDataClass>>> = _threadsState

    init {
        loadThreads()
    }

    private fun loadThreads() {
        db.collection("thread")
            .orderBy("timeCreated", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, error ->
                _threadsState.value = querySnapshot?.documents?.mapNotNull { document ->
                        document.toObject(ThreadDataClass::class.java)?.let { thread ->
                            Pair(document.id, thread)
                        }
                } ?: emptyList()
            }
    }

    fun sendComment(
        authorProfile: ProfileDataClass?,
        commentContent: String,
        coroutineScope: CoroutineScope,
        threadId: String
    ) {
        var answerCount = authorProfile?.numberOfAnswer ?: 0

        coroutineScope.launch {
            val comment = CommentDataClass(
                threadId = threadId,
                fullname = authorProfile?.fullname.orEmpty(),
                username = authorProfile?.username.orEmpty(),
                profilePicture = authorProfile?.profilePicture,
                text = commentContent,
                numberOfLike = 0,
            )

            CommentCollection().addCommentToThread(threadId, comment)

            val updateNumberOfAnswer = mapOf("numberOfAnswer" to (answerCount + 1))
            ProfileCollection().updateProfileInFirestore(
                authorProfile?.userId.toString(),
                updateNumberOfAnswer
            )
            ThreadCollection().updateThreadFirestore(
                threadId, (mapOf("numberOfComment" to FieldValue.increment(1)))
            )
        }
    }
}