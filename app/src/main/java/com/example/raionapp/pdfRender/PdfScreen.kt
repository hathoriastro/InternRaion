package com.example.raionapp.pdfRender

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun PdfScreen(
    modifier: Modifier = Modifier,
    pdfUrl: String // URL PDF dari Supabase/Firestore
) {
    val context = LocalContext.current
    val pdfBitmapConverter = remember { PdfBitmapConverter(context) }
    var renderedPages by remember { mutableStateOf<List<Bitmap>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(pdfUrl) {
        if (pdfUrl.isNotEmpty()) {
            try {
                // 1. Download PDF dari URL ke file sementara
                val tempFile = withContext(Dispatchers.IO) {
                    downloadPdfFromUrl(context, pdfUrl)
                }

                // 2. Konversi PDF ke bitmap
                tempFile?.let { file ->
                    val uri = Uri.fromFile(file)
                    renderedPages = pdfBitmapConverter.pdfToBitmaps(uri)
                }
            } catch (e: Exception) {
                Log.e("PDF Viewer", "Error: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    if (isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(renderedPages) { page ->
                PdfPage(page = page)
            }
        }
    }
}

private suspend fun downloadPdfFromUrl(context: Context, url: String): File? =
    withContext(Dispatchers.IO) {
        try {
            val client = okhttp3.OkHttpClient()
            val request = okhttp3.Request.Builder().url(url).build()
            val response = client.newCall(request).execute()

            val tempFile = File.createTempFile("temp_pdf", ".pdf", context.cacheDir)
            tempFile.outputStream().use { output ->
                response.body?.byteStream()?.copyTo(output)
            }
            tempFile
        } catch (e: Exception) {
            null
        }
    }