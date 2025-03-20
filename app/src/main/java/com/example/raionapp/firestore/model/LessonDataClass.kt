package com.example.raionapp.firestore.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class LessonDataClass(
    val lessonId: String = "",
    val lessonName: String = "",
    val about: String = "",
    val price: String = "",
    val subject: String = "",
    val mentorName: String = "",
    val mentorId: String = "",
    val mentorProfilePicture: String? = null,
    val likeCount: Int = 0,
    val duration: String = "",
    val language: String = "",
    val numberOfSublesson: Int = 0,
    @ServerTimestamp val timeCreated: Date? = null

)

data class SublessonDataClass(
    val subLessonId: String= "",
    val sublessonName: String = "",
    val pokokBahasan: String = "",
    val fileUrl: String = "",
    val fileSummary: String = "",
    val videoUrl: String = "",
    val videoSummary: String = "",
    val videoPracticeUrl: String = "",
    val videoPracticeSummary: String = "",
    @ServerTimestamp val timeCreated: Date? = null
)

