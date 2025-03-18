package com.example.raionapp.presentation.homePage.model

import androidx.lifecycle.ViewModel
import com.example.raionapp.firestore.CommentCollection
import com.example.raionapp.firestore.ProfileCollection
import com.example.raionapp.firestore.ThreadCollection
import com.example.raionapp.firestore.model.CommentDataClass
import com.example.raionapp.firestore.model.ProfileDataClass
import com.example.raionapp.firestore.model.ThreadDataClass
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThreadViewModel : ViewModel() {
    private val db: FirebaseFirestore = Firebase.firestore

    // Gunakan MutableStateFlow untuk menyimpan daftar thread,
    // dengan inisialisasi emptyList()
    private val _threadsState = MutableStateFlow<List<Pair<String, ThreadDataClass>>>(emptyList())
    val threadsState: StateFlow<List<Pair<String, ThreadDataClass>>> = _threadsState.asStateFlow()

    init {
        loadThreads()
    }

    // Fungsi untuk memuat thread secara real time dan terurut berdasarkan timeCreated (terbaru di atas)
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
