package com.example.raionapp.Firestore.Model

data class CommentDataClass(
    val commentId: String,
    val threadId: ThreadDataClass,
    val fullname: ProfileDataClass,
    val username: ProfileDataClass,
    val text: String,
    val numberOfLike: Int, // Otomatis jika likenye > 0 maka akan muncul icon like
    val profilePicture: ProfileDataClass,
//    val saveIcon: Boolean, // Apakah perlu?? Bagaimana cara menyimpan data ini di Firestore?
//    val likeIcon: Boolean, // Apakah perlu?? Bagaimana cara menyimpan data ini di Firestore?
)
