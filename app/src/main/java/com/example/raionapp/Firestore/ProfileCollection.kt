package com.example.raionapp.Firestore

import android.content.ContentValues.TAG
import android.util.Log
import com.example.raionapp.Firestore.Model.ProfileDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ProfileCollection {
    val db = Firebase.firestore

    suspend fun addProfileToFirestore(profile: ProfileDataClass){
        val profileHashMap = hashMapOf(
            "userId" to profile.userId,
            "username" to profile.username,
            "fullName" to profile.fullName,
            "email" to profile.email,
            "numberOfQuestion" to profile.numberOfQuestion,
            "numberOfAnswer" to profile.numberOfAnswer,
            "profilePicture" to profile.profilePicture
        )

        if (profile.userId.isNullOrEmpty()) {
            db.collection("profile")
                .document(profile.userId)
                .set(profileHashMap)
                .addOnSuccessListener { document ->
                    Log.d(TAG, "DocumentSnapshot added with ID: $document")
                }.addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }.await()
        }
    }
}