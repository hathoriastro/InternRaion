package com.example.raionapp.firestore.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

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