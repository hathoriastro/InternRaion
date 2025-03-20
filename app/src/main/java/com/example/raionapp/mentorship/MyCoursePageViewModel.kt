package com.example.raionapp.mentorship

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionapp.firestore.model.LessonDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyCoursePageViewModel: ViewModel() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _myCourseState = MutableStateFlow<List<Pair<String, LessonDataClass>>>(emptyList())
    val myCourseState: StateFlow<List<Pair<String, LessonDataClass>>> = _myCourseState

    fun loadMentorClass(userId: String) {
        viewModelScope.launch {
            db.collection("lessons")
                .whereEqualTo("mentorId", userId)
                .orderBy("timeCreated", Query.Direction.DESCENDING)
                .addSnapshotListener { querySnapshot, _ ->
                    _myCourseState.value = querySnapshot?.documents?.mapNotNull {document ->
                        document.toObject(LessonDataClass::class.java)?.let {lesson ->
                            Pair(document.id, lesson)
                        }
                    } ?: emptyList()
                }

        }
    }
}