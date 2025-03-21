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
    val imageURL: String? = null,
    val semester: String = "",
    val subject: String = "",
    @ServerTimestamp val timeCreated: Date? = null
)