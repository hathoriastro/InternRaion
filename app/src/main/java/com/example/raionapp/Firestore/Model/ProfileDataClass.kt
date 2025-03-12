package com.example.raionapp.Firestore.Model

data class ProfileDataClass(
    val userId: String,
    val username: String?,
    val fullName: String?,
    val email: String,
    val numberOfQuestion: Int = 0,
    val numberOfAnswer: Int = 0,
    val profilePicture: String? // ????
)
