package com.example.raionapp.firestore.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class CommentDataClass(
    val commentId: String = "",
    val threadId: String = "",
    val fullname: String = "",
    val username: String = "",
    val text: String = "",
    val numberOfLike: Int = 0,
    val profilePicture: String? = null,
    @ServerTimestamp val timeCreated: Date? = null
)
