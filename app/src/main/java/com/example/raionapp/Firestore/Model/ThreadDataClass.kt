package com.example.raionapp.Firestore.Model

data class ThreadDataClass(
    val threadId: String?,
    val userId: ProfileDataClass?,
    val fullname: ProfileDataClass?,
    val username: ProfileDataClass?,
    val text: String?,
    val image: String?,
    val numberOfLike: Int?, // Otomatis jika likenye > 0 maka akan muncul icon like
    val numberOfComment: Int?, // Otomatis jika commentnya > 0 maka akan muncul icon comment
//    val saveIcon: Boolean?, // Apakah perlu?? Bagaimana cara menyimpan data ini di Firestore?
//    val likeIcon: Boolean?, // Apakah perlu?? Bagaimana cara menyimpan data ini di Firestore?
)