package com.example.raionapp.presentation.homePage.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionapp.firestore.LessonCollection
import com.example.raionapp.firestore.model.LessonDataClass
import com.example.raionapp.firestore.model.ProfileDataClass
import com.example.raionapp.firestore.model.SublessonDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LearningPageViewModel: ViewModel() {
    private val db: FirebaseFirestore = Firebase.firestore

    private val _learningState = MutableStateFlow<List<Pair<String, LessonDataClass>>>(emptyList())
    val learningState: StateFlow<List<Pair<String, LessonDataClass>>> = _learningState.asStateFlow()

    fun loadLesson(mentorId: String?) {
        var query = db.collection("lesson")

        if (mentorId.isNullOrEmpty()) {
            query = query.whereEqualTo("mentorId", mentorId) as CollectionReference
        }

        query.orderBy("timeCreated", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, _ ->
                _learningState.value = querySnapshot?.documents?.mapNotNull { document ->
                    document.toObject(LessonDataClass::class.java)?.let { lesson ->
                        Pair(document.id, lesson)
                    }
                } ?: emptyList()
            }
    }

    fun sendLesson(
        authorProfile: ProfileDataClass?,
        lessonName: String,
        about: String,
        price: String,
        subject: String,
        duration: String,
        language: String,
        numberOfSublesson: Int,
    ){
        viewModelScope.launch {
            val lesson = LessonDataClass(
                lessonName = lessonName,
                about = about,
                price = price,
                subject = subject,
                mentorName = authorProfile?.fullname.orEmpty(),
                mentorId = authorProfile?.userId.orEmpty(),
                mentorProfilePicture = authorProfile?.profilePicture.orEmpty(),
                likeCount = 0,
                duration = duration,
                language = language,
                numberOfSublesson = numberOfSublesson
            )
            LessonCollection().addLessonToFirestore(lesson)
        }
    }

    fun sendSubLesson(
        lessonId: String,
        sublessonName: String,
        pokokBahasan: String,
        fileUrl: String,
        fileSummary: String,
        videoUrl: String,
        videoSummary: String,
        videoPracticeUrl: String,
        videoPracticeSummary: String
    ){
        viewModelScope.launch {
            val subLesson = SublessonDataClass(
                sublessonName = sublessonName,
                pokokBahasan = pokokBahasan,
                fileUrl = fileUrl,
                fileSummary = fileSummary,
                videoUrl = videoUrl,
                videoSummary = videoSummary,
                videoPracticeUrl = videoPracticeUrl,
                videoPracticeSummary = videoPracticeSummary
            )
            LessonCollection().addSubLessonToFirestore(
                lessonId = lessonId,
                subLesson = subLesson
            )
        }
    }

    fun updateLesson(
        lessonId: String,
        updateData: Map<String, Any>
    ) {
        viewModelScope.launch {
            LessonCollection().updateLesson(lessonId, updateData)
        }
    }

    fun updateSubLesson(
        lessonId: String,
        subLessonId: String,
        updateData: Map<String, Any>
    ){
        viewModelScope.launch {
            LessonCollection().updateSubLesson(lessonId, subLessonId, updateData)
        }
    }
}