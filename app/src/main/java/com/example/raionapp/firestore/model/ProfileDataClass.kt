package com.example.raionapp.firestore.model

data class ProfileDataClass(
    val userId: String = "",
    val username: String? = null,
    val fullname: String? = null,
    val email: String = "",
    val numberOfQuestion: Int = 0,
    val numberOfAnswer: Int = 0,
    val profilePicture: String? = null,
    val role: String? = null
) {
    // Untuk deserialisasi Firestore
    constructor() : this("", null, null, "", 0, 0, null, null)
}

