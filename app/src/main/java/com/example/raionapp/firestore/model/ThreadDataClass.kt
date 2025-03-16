package com.example.raionapp.firestore.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class ThreadDataClass(
    val threadId: String = "",
    val userId: String = "",
    val fullname: String = "",
    val username: String = "",
    val threadText: String = "",
    val authorProfilePicture: String? = null,
    val numberOfLike: Int = 0,
    val numberOfComment: Int = 0,
    @ServerTimestamp val timeCreated: Date? = null
//    val saveIcon: Boolean?, // Apakah perlu?? Bagaimana cara menyimpan data ini di Firestore?
//    val likeIcon: Boolean?, // Apakah perlu?? Bagaimana cara menyimpan data ini di Firestore?
)