package com.example.raionapp.firestore

import android.util.Log
import com.example.raionapp.firestore.model.LessonDataClass
import com.example.raionapp.firestore.model.SublessonDataClass
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class LessonCollection {
    val db = Firebase.firestore

    suspend fun addLessonToFirestore(lesson: LessonDataClass) {
        val lessonHashMap = hashMapOf(
            "lessonId" to lesson.lessonId,
            "lessonName" to lesson.lessonName,
            "about" to lesson.about,
            "numberOfMaterial" to lesson.numberOfMaterial,
            "price" to lesson.price,
            "subject" to lesson.subject,
            "mentorName" to lesson.mentorName,
            "mentorId" to lesson.mentorId,
            "mentorProfilePicture" to lesson.mentorProfilePicture,
            "language" to lesson.language,
            "likeCount" to lesson.likeCount,
            "duration" to lesson.duration,
            "numberOfSublesson" to lesson.numberOfSublesson,
            "timeCreated" to FieldValue.serverTimestamp(),
            "classMember" to lesson.classMember
        )

        val documentId = db.collection("lesson").add(lessonHashMap).await()
        val getDocumentId = documentId.id

        db.collection("lesson")
            .document(getDocumentId)
            .update("lessonId", getDocumentId)
            .addOnCompleteListener {
                Log.d("LessonCollection", "Lesson berhasil disimpan dengan ID: $getDocumentId")
            }.addOnFailureListener { e ->
                Log.w("LessonCollection", "Gagal menambahkan lesson ", e)
            }
    }

    suspend fun addSubLessonToFirestore(lessonId: String, subLesson: SublessonDataClass){
        val subLessonHashMap = hashMapOf(
            "subLessonId" to subLesson.subLessonId,
            "sublessonName" to subLesson.sublessonName,
            "pokokBahasan" to subLesson.pokokBahasan,
            "fileUrl" to subLesson.fileUrl,
            "fileSummary" to subLesson.fileSummary,
            "videoUrl" to subLesson.videoUrl,
            "videoSummary" to subLesson.videoSummary,
            "videoPracticeUrl" to subLesson.videoPracticeUrl,
            "videoPracticeSummary" to subLesson.videoPracticeSummary,

            "timeCreated" to FieldValue.serverTimestamp()
        )

        val documentId = db.collection("lesson")
            .document(lessonId)
            .collection("sublesson")
            .add(subLessonHashMap)
            .await()

        val getDocumentId = documentId.id

        db.collection("lesson")
            .document(lessonId)
            .collection("sublesson")
            .document(getDocumentId)
            .update("subLessonId", getDocumentId)
            .addOnCompleteListener {
                Log.d("LessonCollection", "Sublesson berhasil disimpan dengan ID: $getDocumentId")
            }.addOnFailureListener { e ->
                Log.w("LessonCollection", "Gagal menambahkan sublesson ", e)
            }
    }

    suspend fun updateLesson(
        lessonId: String,
        updateData: Map<String, Any>
    ){
        db.collection("lesson")
            .document(lessonId)
            .update(updateData)
            .addOnSuccessListener { document ->
                Log.d("LessonCollection", "Lesson berhasil diupdate dengan ID: $lessonId")
            }.addOnFailureListener { e ->
                Log.w("LessonCollection", "Error updating document", e)
            }.await()
    }

    suspend fun updateSubLesson(
        lessonId: String,
        subLessonId: String,
        updateData: Map<String, Any>
    ){
        db.collection("lesson")
            .document(lessonId)
            .collection("sublesson")
            .document(subLessonId)
            .update(updateData)
            .addOnSuccessListener { document ->
                Log.d("SubLessonCollection", "Sublesson berhasil diupdate dengan ID: $subLessonId")
            }.addOnFailureListener { e ->
                Log.w("SubLessonCollection", "Error updating document", e)
            }.await()

    }


}