package com.example.raionapp.Firestore.Model

data class ProfileDataClass(
    val userId: String,
    val username: String?,
    val fullname: String?,
    val email: String,
    val numberOfQuestion: Int,
    val numberOfAnswer: Int,
    val profilePicture: String?, // ????
    val role: String
)
