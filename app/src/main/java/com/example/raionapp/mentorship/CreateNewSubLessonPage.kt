package com.example.raionapp.mentorship

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.firestore.model.LessonDataClass
import com.example.raionapp.presentation.homePage.model.LearningPageViewModel
import com.example.raionapp.presentation.homePage.model.profileData
import com.example.raionapp.presentation.register.AuthViewModel
import android.Manifest
import android.widget.Toast

@Preview(showBackground = true)
@Composable
fun CreateNewSubLessonPagePreview(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null
) {
    CreateNewSubLessonPage(
        navController = navController,
        lessonId = ""
    )
}

@Composable
fun CreateNewSubLessonPage(
    navController: NavHostController,
    lessonId: String
) {
    val learningPageViewModel: LearningPageViewModel = viewModel()
    val context = LocalContext.current

    var sublessonName by remember { mutableStateOf("") }
    var pokokBahasan by remember { mutableStateOf("") }
    var fileUrl by remember { mutableStateOf("") }
    var fileSummary by remember { mutableStateOf("") }
    var videoUrl by remember { mutableStateOf("") }
    var videoSummary by remember { mutableStateOf("") }
    var videoPracticeUrl by remember { mutableStateOf("") }
    var videoPracticeSummary by remember { mutableStateOf("") }

    var selectedVideoUri by remember { mutableStateOf<Uri?>(null) }
    var selectedPdfUri by remember { mutableStateOf<Uri?>(null) }

    var isPractice by remember { mutableStateOf(false) }

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedVideoUri = it
            learningPageViewModel.uploadVideoFromUri(
                uri = it,
                context = context,
                onUploadComplete = { uploadedUrl ->
                    if (!isPractice) {
                        videoUrl = uploadedUrl
                    } else {
                        videoPracticeUrl = uploadedUrl
                    }
                    Toast.makeText(context, "Video berhasil diupload", Toast.LENGTH_SHORT).show()
                },
                onUploadFailed = { errorMessage ->
                    Toast.makeText(context, "Video Gagal Diupload", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            videoPickerLauncher.launch("video/*")
        }
    }


    val pdfPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedPdfUri = it
            learningPageViewModel.uploadPdfFromUri(
                uri = it,
                context = context,
                onUploadComplete = { uploadedUrl ->
                    fileUrl = uploadedUrl
                    Toast.makeText(context, "PDF berhasil diupload", Toast.LENGTH_SHORT).show()
                },
                onUploadFailed = { errorMessage ->
                    Toast.makeText(context, "Gagal upload PDF", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ){
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(24.dp))
            ){
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow_icon),
                    contentDescription = "image description",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterStart)
                        .padding(start = 40.dp, top = 20.dp)
                        .clickable { navController.popBackStack() },
                    tint = Color.White
                )

                Text(
                    text = "Create New Lesson",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 20.dp)
                )
            }


            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Lesson Name",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = sublessonName,
                        onValueChange = {
                            sublessonName = it
                        },
                        placeholder = {
                            Text(
                                text = "Example: Learn UI/UX from Scratch",
                                // Body Text/Body Small Medium
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF757575),
                                )
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF0F1F5),
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    Modifier.fillMaxWidth(),
                ) {
                    Spacer(Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "About this Lesson",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = pokokBahasan,
                        onValueChange = {
                            pokokBahasan = it
                        },
                        placeholder = {
                            Text(
                                text = "Briefly describe this lesson",
                                // Body Text/Body Small Medium
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF757575),
                                )
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF0F1F5),
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Upload File",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(Modifier.height(10.dp))
                    Box(
                        Modifier
                            .shadow(
                                elevation = 2.dp,
                                spotColor = Color(0x3DE4E5E7),
                                ambientColor = Color(0x3DE4E5E7)
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .width(100.dp)
                            .height(46.dp)
                            .background(
                                color = Color(0xFFF0F1F5),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .clickable {
                                pdfPickerLauncher.launch("application/pdf")
                            }

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.upload_file_icon),
                            contentDescription = null,
                            Modifier
                                .align(Alignment.CenterStart)
                                .offset(x = 5.dp)
                        )
                        Text(
                            text = "Upload",

                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF757575),
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(10.dp)
                        )

                    }
                }

                Column(
                    Modifier.fillMaxWidth(),
                ) {
                    Spacer(Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "About File (optional)",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = fileSummary,
                        onValueChange = {
                            fileSummary = it
                        },
                        placeholder = {
                            Text(
                                text = "Describe this file",
                                // Body Text/Body Small Medium
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF757575),
                                )
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF0F1F5),
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Upload Video",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(Modifier.height(10.dp))
                    Box(
                        Modifier
                            .shadow(
                                elevation = 2.dp,
                                spotColor = Color(0x3DE4E5E7),
                                ambientColor = Color(0x3DE4E5E7)
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .width(100.dp)
                            .height(46.dp)
                            .background(
                                color = Color(0xFFF0F1F5),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .clickable {
                                isPractice = false
                                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            }

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.upload_file_icon),
                            contentDescription = null,
                            Modifier
                                .align(Alignment.CenterStart)
                                .offset(x = 5.dp)
                        )
                        Text(
                            text = "Upload",

                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF757575),
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(10.dp)
                        )

                    }
                }

                Column(
                    Modifier.fillMaxWidth(),
                ) {
                    Spacer(Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "About Video (optional)",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = videoSummary,
                        onValueChange = {
                            videoSummary = it
                        },
                        placeholder = {
                            Text(
                                text = "Describe this video",
                                // Body Text/Body Small Medium
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF757575),
                                )
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF0F1F5),
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Upload Video Practice",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(Modifier.height(10.dp))
                    Box(
                        Modifier
                            .shadow(
                                elevation = 2.dp,
                                spotColor = Color(0x3DE4E5E7),
                                ambientColor = Color(0x3DE4E5E7)
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Transparent,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .width(100.dp)
                            .height(46.dp)
                            .background(
                                color = Color(0xFFF0F1F5),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .clickable {
                                isPractice = true
                                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            }

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.upload_file_icon),
                            contentDescription = null,
                            Modifier
                                .align(Alignment.CenterStart)
                                .offset(x = 5.dp)
                        )
                        Text(
                            text = "Upload",

                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF757575),
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(10.dp)
                        )

                    }
                }

                Column(
                    Modifier.fillMaxWidth(),
                ) {
                    Spacer(Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "About Practice Video (optional)",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = videoPracticeSummary,
                        onValueChange = {
                            videoPracticeSummary = it
                        },
                        placeholder = {
                            Text(
                                text = "Describe this practice video",
                                // Body Text/Body Small Medium
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF757575),
                                )
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF0F1F5),
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Spacer(Modifier.height(30.dp))
                
                Box(
                    Modifier
                        .shadow(
                            elevation = 2.dp,
                            spotColor = Color(0x3DE4E5E7),
                            ambientColor = Color(0x3DE4E5E7)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .width(332.dp)
                        .height(46.dp)
                        .background(
                            color = Color(0xFF1A5294),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .clickable {
                            learningPageViewModel.sendSubLesson(
                                lessonId = lessonId,
                                sublessonName = sublessonName,
                                pokokBahasan = pokokBahasan,
                                fileUrl = fileUrl,
                                fileSummary = fileSummary,
                                videoUrl = videoUrl,
                                videoSummary = videoSummary,
                                videoPracticeUrl = videoPracticeUrl,
                                videoPracticeSummary = videoPracticeSummary
                            )
                            navController.navigate("lessonpageunlocked/$lessonId")
                        },
                ){
                    Text(
                        text = "Create Class",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Spacer(Modifier.height(30.dp))
            }
        }
    }
}