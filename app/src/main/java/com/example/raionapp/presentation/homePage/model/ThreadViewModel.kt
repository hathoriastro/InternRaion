package com.example.raionapp.presentation.homePage.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.raionapp.firestore.model.ThreadDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

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
}