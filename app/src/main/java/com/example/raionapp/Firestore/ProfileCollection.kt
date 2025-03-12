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

        if (!profile.userId.isNullOrEmpty()) {
            db.collection("profile")
                .document(profile.userId)
                .set(profileHashMap)
                .addOnSuccessListener { document ->
                    Log.d("ProfileCollection", "Dokumen berhasil disimpan dengan ID: ${profile.userId}")
                }.addOnFailureListener { e ->
                    Log.w("ProfileCollection", "Error adding document", e)
                }.await()
        }
        else {
            Log.d("ProfileCollection", "User ID tidak boleh kosong")
        }
    }

//    Untuk melakukan Update Profile di Firestore
    suspend fun updateProfileInFirestore(
        userId: String,
        updateData: Map<String, Any>
    ) {
        db.collection("profile")
            .document(userId)
            .update(updateData)
            .addOnSuccessListener { document ->
                Log.d(TAG, "Dokumen berhasil diupdate dengan ID: $userId")
            }.addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }.await()
    }

//    Untuk mengambil data Profile dari Firestore
    suspend fun getProfileFromFirestore(userId: String): ProfileDataClass? {
        val documentSnapshot = db.collection("profile")
            .document(userId)
            .get()
            .addOnSuccessListener {
                Log.d("ProfileCollection", "Dokumen berhasil diambil dengan ID: $userId")
            }.addOnFailureListener { e ->
                Log.w("ProfileCollection", "Gagal mengambil dokumen", e)
            }.await()
        return if (documentSnapshot.exists()) {
            documentSnapshot.toObject(ProfileDataClass::class.java)
        } else {
            null
        }
    }
}