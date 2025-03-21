package com.example.raionapp.supabase

import android.content.Context
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun uriToFile(uri: Uri, context: Context): File? {
    return try {
        val contentResolver = context.contentResolver
        val originalExtension = contentResolver.getType(uri)?.split("/")?.last() ?: "jpg"

        val inputStream = contentResolver.openInputStream(uri) ?: return null
        val tempFile = File.createTempFile("upload", ".$originalExtension", context.cacheDir)

        tempFile.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
        tempFile
    } catch (e: Exception) {
        Log.e("UriToFile", "Error: ${e.message}")
        null
    }
}

suspend fun uploadImageToStorage(imageFile: File, bucket: String = "images"): String? = withContext(Dispatchers.IO) {
    val supabaseUrl = "https://alttsnvqrvubfyznbmji.supabase.co"
    val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFsdHRzbnZxcnZ1YmZ5em5ibWppIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIyOTEzNzgsImV4cCI6MjA1Nzg2NzM3OH0.dFQVX797frNfOcIOsCCR7C7N7w27j7PSaWFN35E_53A" // Pastikan key benar

    val extension = imageFile.extension
    val mediaType = when (extension.lowercase()) {
        "jpg", "jpeg" -> "image/jpeg"
        "png" -> "image/png"
        "gif" -> "image/gif"
        "webp" -> "image/webp"
        else -> "image/*"
    }

    val requestBody = imageFile.asRequestBody(mediaType.toMediaTypeOrNull())

    val request = Request.Builder()
        .url("$supabaseUrl/storage/v1/object/$bucket/${imageFile.name}")
        .addHeader("Authorization", "Bearer $supabaseKey")
        .addHeader("Content-Type", "image/*")
        .post(requestBody)
        .build()

    val client = OkHttpClient()
    client.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
            "$supabaseUrl/storage/v1/object/public/$bucket/${imageFile.name}"
        } else {
            Log.e("UploadError", "Gagal upload: ${response.body?.string()}")
            null
        }
    }
}

suspend fun uploadVideoToStorage(videoFile: File, bucket: String = "video"): String? = withContext(Dispatchers.IO) {
    val supabaseUrl = "https://alttsnvqrvubfyznbmji.supabase.co"
    val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFsdHRzbnZxcnZ1YmZ5em5ibWppIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIyOTEzNzgsImV4cCI6MjA1Nzg2NzM3OH0.dFQVX797frNfOcIOsCCR7C7N7w27j7PSaWFN35E_53A"

    val requestBody = videoFile.asRequestBody("video/*".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("$supabaseUrl/storage/v1/object/$bucket/${videoFile.name}")
        .addHeader("Authorization", "Bearer $supabaseKey")
        .addHeader("Content-Type", "video/*")
        .post(requestBody)
        .build()

    val client = OkHttpClient()
    client.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
            "$supabaseUrl/storage/v1/object/public/$bucket/${videoFile.name}"
        } else {
            Log.e("VideoUploadError", "Gagal upload: ${response.body?.string()}")
            null
        }
    }
}

suspend fun uploadPdfToStorage(pdfFile: File, bucket: String = "pdf"): String? = withContext(Dispatchers.IO) {
    val supabaseUrl = "https://alttsnvqrvubfyznbmji.supabase.co"
    val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFsdHRzbnZxcnZ1YmZ5em5ibWppIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIyOTEzNzgsImV4cCI6MjA1Nzg2NzM3OH0.dFQVX797frNfOcIOsCCR7C7N7w27j7PSaWFN35E_53A"

    val requestBody = pdfFile.asRequestBody("application/pdf".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("$supabaseUrl/storage/v1/object/$bucket/${pdfFile.name}")
        .addHeader("Authorization", "Bearer $supabaseKey")
        .addHeader("Content-Type", "application/pdf")
        .post(requestBody)
        .build()

    val client = OkHttpClient()
    client.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
            "$supabaseUrl/storage/v1/object/public/$bucket/${pdfFile.name}"
        } else {
            Log.e("PDFUploadError", "Gagal upload: ${response.body?.string()}")
            null
        }
    }
}