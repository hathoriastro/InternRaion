package com.example.raionapp.presentation.homePage.model

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.example.raionapp.supabase.uploadImageToStorage
import com.example.raionapp.supabase.uriToFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ImagePickerHandler(
    val galleryLauncher: ActivityResultLauncher<String>,
    val cameraLauncher: ActivityResultLauncher<Uri>,
    val permissionLauncher: ActivityResultLauncher<String>,
    val tempImageUri: Uri?
)

@Composable
fun rememberImagePicker(
    coroutineScope: CoroutineScope,
    onImageUploaded: (String?) -> Unit
): ImagePickerHandler {
    val context = LocalContext.current
    var tempImageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            coroutineScope.launch {
                val file = uriToFile(it, context)
                file?.let { f ->
                    val url = uploadImageToStorage(f)
                    onImageUploaded(url)
                    Toast.makeText(context, "Gambar berhasil diupload", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            tempImageUri?.let { uri ->
                coroutineScope.launch {
                    val file = uriToFile(uri, context)
                    file?.let { f ->
                        val url = uploadImageToStorage(f)
                        onImageUploaded(url)
                        Toast.makeText(context, "Gambar berhasil diupload", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val file = createImageFile(context)
            tempImageUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )
            cameraLauncher.launch(tempImageUri!!)
        } else {
            Toast.makeText(context, "Izin kamera diperlukan", Toast.LENGTH_SHORT).show()
        }
    }

    return ImagePickerHandler(
        galleryLauncher = galleryLauncher,
        cameraLauncher = cameraLauncher,
        permissionLauncher = permissionLauncher,
        tempImageUri = tempImageUri
    )
}

@SuppressLint("SimpleDateFormat")
private fun createImageFile(context: Context): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    )
}