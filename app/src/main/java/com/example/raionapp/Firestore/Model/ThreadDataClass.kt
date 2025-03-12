package com.example.raionapp.Firestore.Model

data class ThreadDataClass(
    val author: ProfileDataClass,
    val text: String,
    // Upload Content?? Image/Video/Document
    val numberOfComment: Int
)