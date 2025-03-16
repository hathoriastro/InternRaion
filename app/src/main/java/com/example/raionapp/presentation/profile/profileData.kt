package com.example.raionapp.presentation.profile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.raionapp.firestore.model.ProfileDataClass
import com.example.raionapp.firestore.ProfileCollection
import com.example.raionapp.presentation.authentication.AuthViewModel

@Composable
fun profileData(authViewModel: AuthViewModel?): State<ProfileDataClass?> {
    val profileData = remember { mutableStateOf<ProfileDataClass?>(null) }
    val userId = authViewModel?.auth?.currentUser?.uid

    LaunchedEffect(userId) {
        userId?.let { id ->
            try {
                val firestoreProfileData = ProfileCollection().getProfileFromFirestore(id)
                profileData.value = firestoreProfileData
                Log.d("ProfileData", "Data Profile: $firestoreProfileData")
            } catch (e: Exception) {
                Log.e("ProfileData", "Error: ${e.message}")
            }
        }
    }
    return profileData
}