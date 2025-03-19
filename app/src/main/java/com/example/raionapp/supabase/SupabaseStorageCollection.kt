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

// Fungsi untuk mengonversi URI ke File (file sementara di cache)
fun uriToFile(uri: Uri, context: Context): File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val tempFile = File.createTempFile("upload", ".jpg", context.cacheDir)
        tempFile.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
        tempFile
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

suspend fun uploadImageToStorage(imageFile: File, bucket: String = "images"): String? = withContext(Dispatchers.IO) {
    val supabaseUrl = "https://alttsnvqrvubfyznbmji.supabase.co"
    val supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFsdHRzbnZxcnZ1YmZ5em5ibWppIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIyOTEzNzgsImV4cCI6MjA1Nzg2NzM3OH0.dFQVX797frNfOcIOsCCR7C7N7w27j7PSaWFN35E_53A" // Pastikan key benar

    // Gunakan endpoint upload yang benar
    val uploadUrl = "$supabaseUrl/storage/v1/object/$bucket/${imageFile.name}"

    val client = OkHttpClient()
    val mediaType = "image/*".toMediaTypeOrNull()
    val requestBody = imageFile.asRequestBody(mediaType)

    val request = Request.Builder()
        .url(uploadUrl)
        .addHeader("Authorization", "Bearer $supabaseKey") // Wajib!
        .addHeader("Content-Type", "image/*")
        .post(requestBody) // Supabase menggunakan POST untuk upload
        .build()

    client.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
            // Return public URL yang benar
            "$supabaseUrl/storage/v1/object/public/$bucket/${imageFile.name}"
        } else {
            Log.e("UploadError", "Gagal upload: ${response.body?.string()}")
            null
        }
    }
}