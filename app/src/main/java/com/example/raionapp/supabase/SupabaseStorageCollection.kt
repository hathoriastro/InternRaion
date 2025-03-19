import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

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

// Fungsi untuk menyimpan Bitmap ke File
fun bitmapToFile(bitmap: Bitmap, context: Context): File? {
    return try {
        val tempFile = File.createTempFile("camera_upload", ".jpg", context.cacheDir)
        val out = FileOutputStream(tempFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.flush()
        out.close()
        tempFile
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

// Fungsi untuk mengunggah gambar ke Supabase Storage dan mengembalikan URL
suspend fun uploadImageToStorage(imageFile: File, bucket: String = "images"): String?  = withContext(Dispatchers.IO) {
    val supabaseUrl = "https://alttsnvqrvubfyznbmji.supabase.co" // Ganti dengan URL Supabase proyek Anda
    // URL endpoint untuk mengunggah file ke bucket "images"
    val uploadUrl = "$supabaseUrl/storage/v1/object/public/$bucket/${imageFile.name}"
    val client = OkHttpClient()
    val mediaType = "image/jpeg".toMediaTypeOrNull() // Sesuaikan jika file berupa PNG misalnya
    val requestBody = imageFile.asRequestBody(mediaType)

    val request = Request.Builder()
        .url(uploadUrl)
        .put(requestBody)
        .build()

    client.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
            Log.d("UploadImageToStorage", "Upload berhasil: $uploadUrl")
            uploadUrl // URL file yang dapat diakses
        } else {
            null
        }
    }
}
