package com.example.raionapp.presentation.homePage.model

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionapp.firestore.LessonCollection
import com.example.raionapp.firestore.model.LessonDataClass
import com.example.raionapp.firestore.model.ProfileDataClass
import com.example.raionapp.firestore.model.SublessonDataClass
import com.example.raionapp.supabase.uploadPdfToStorage
import com.example.raionapp.supabase.uploadVideoToStorage
import com.example.raionapp.supabase.uriToFile
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LearningPageViewModel: ViewModel() {
    private val db: FirebaseFirestore = Firebase.firestore

    private val _learningState = MutableStateFlow<List<Pair<String, LessonDataClass>>>(emptyList())
    val learningState: StateFlow<List<Pair<String, LessonDataClass>>> = _learningState.asStateFlow()

    private val _lessonDetailsState = MutableStateFlow<LessonDataClass?>(null)
    val lessonDetailsState: StateFlow<LessonDataClass?> = _lessonDetailsState.asStateFlow()

    private val _subLessonState = MutableStateFlow<List<SublessonDataClass>>(emptyList())
    val subLessonState: StateFlow<List<SublessonDataClass>> = _subLessonState.asStateFlow()

    init {
        loadLessonHome()
    }

    private fun loadLessonHome() {
        db.collection("lesson")
        .orderBy("timeCreated", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, _ ->
                _learningState.value = querySnapshot?.documents?.mapNotNull { document ->
                    document.toObject(LessonDataClass::class.java)?.let { lesson ->
                        Pair(document.id, lesson)
                    }
                } ?: emptyList()
            }
    }

    fun loadLessonDetails(lessonId: String) {
        db.collection("lesson")
            .document(lessonId)
            .addSnapshotListener { documentSnapshot, _ ->
                _lessonDetailsState.value = documentSnapshot?.toObject(LessonDataClass::class.java)
            }
    }

    fun loadSubLesson(lessonId: String) {
        db.collection("lesson")
            .document(lessonId)
            .collection("sublesson")
            .orderBy("timeCreated", Query.Direction.DESCENDING)
            .addSnapshotListener { querySnapshot, _ ->
                _subLessonState.value = querySnapshot?.documents?.mapNotNull { document ->
                    document.toObject(SublessonDataClass::class.java) // Ganti ke SublessonDataClass
                } ?: emptyList()
            }
    }

    fun loadFilteredVideoSubLesson(lessonId: String, isPractice: Boolean = false) {
        db.collection("lessons")
            .document(lessonId)
            .collection("sublessons")
            .addSnapshotListener { value, error ->
                val filteredList = value?.documents?.mapNotNull { doc ->
                    doc.toObject(SublessonDataClass::class.java)
                }?.filter { subLesson ->
                    if (isPractice) {
                        subLesson.videoPracticeUrl.isNotEmpty() // Video practice
                    } else {
                        subLesson.videoUrl.isNotEmpty() // Video biasa
                    }
                }
                _subLessonState.value = filteredList ?: emptyList()
            }
    }

    fun checkClassMembership(userId: String?): Boolean {
        return _lessonDetailsState.value?.classMember?.contains(userId) == true
    }

    fun checkClassMentorship(userId: String?): Boolean {
        return _lessonDetailsState.value?.mentorId == userId
    }

    fun sendLesson(
        authorProfile: ProfileDataClass?,
        lessonName: String,
        about: String,
        price: String,
        subject: String,
        duration: String,
        language: String,
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

    fun uploadVideoFromUri(
        uri: Uri,
        context: Context,
        onUploadComplete: (String) -> Unit,
        onUploadFailed: (String) -> Unit = {}
    ) {
        val videoFile = uriToFile(uri, context)
        if (videoFile != null) {
            viewModelScope.launch {
                val uploadedUrl = uploadVideoToStorage(videoFile)
                if (uploadedUrl != null) {
                    onUploadComplete(uploadedUrl)
                } else {
                    onUploadFailed("Gagal mengupload video.")
                }
            }
        } else {
            onUploadFailed("Gagal mengonversi Uri ke File.")
        }
    }

    fun uploadPdfFromUri(
        uri: Uri,
        context: Context,
        onUploadComplete: (String) -> Unit,
        onUploadFailed: (String) -> Unit = {}
    ) {
        val pdfFile = uriToFile(uri, context)
        if (pdfFile != null) {
            viewModelScope.launch {
                val uploadedUrl = uploadPdfToStorage(pdfFile)
                if (uploadedUrl != null) {
                    onUploadComplete(uploadedUrl)
                } else {
                    onUploadFailed("Gagal mengupload PDF")
                }
            }
        } else {
            onUploadFailed("Gagal mengonversi Uri ke File")
        }
    }
}