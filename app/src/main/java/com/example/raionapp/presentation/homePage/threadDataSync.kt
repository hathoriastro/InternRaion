package com.example.raionapp.presentation.homePage

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.raionapp.Firestore.Model.ThreadDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

@Composable
fun threadDataSync(): List<Pair<String, ThreadDataClass>>  {
    val threadData = remember {
        mutableStateOf<List<Pair<String, ThreadDataClass>>>(emptyList())
    }
    val firestore = FirebaseFirestore.getInstance()

    LaunchedEffect(Unit) {
        firestore.collection("thread")
            .orderBy("timeCreated", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    Log.e("ThreadsList", "Error fetching threads: ${error.message}")
                    return@addSnapshotListener
                }
                querySnapshot?.let { snapshot ->
                    // Konversi setiap document menjadi ThreadDataClass dan ambil document.id sebagai threadId
                    val threads = snapshot.documents.mapNotNull { document ->
                        document.toObject(ThreadDataClass::class.java)?.let { thread ->
                            Pair(document.id, thread)
                        }
                    }
                    threadData.value = threads
                }
            }
    }
    return threadData.value
}