package com.example.raionapp.Firestore.Model

data class ThreadDataClass(
    val threadId: String = "",
    val userId: String = "",
    val fullname: String = "",
    val username: String = "",
    val threadText: String = "",
    val authorProfilePicture: String? = null,
    val numberOfLike: Int = 0,
    val numberOfComment: Int = 0
//    val saveIcon: Boolean?, // Apakah perlu?? Bagaimana cara menyimpan data ini di Firestore?
//    val likeIcon: Boolean?, // Apakah perlu?? Bagaimana cara menyimpan data ini di Firestore?
)