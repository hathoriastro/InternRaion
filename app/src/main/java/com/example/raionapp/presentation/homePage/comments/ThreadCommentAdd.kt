package com.example.raionapp.presentation.homePage.comments

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionapp.R
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.register.AuthViewModel
import com.example.raionapp.presentation.homePage.model.CommentViewModel
import com.example.raionapp.presentation.profile.profileData
import com.example.raionapp.utils.rememberImagePicker

@Composable
fun ThreadCommentAdd(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
    threadId: String
) {
    var comment by remember { mutableStateOf("") }

    val authorProfileData = profileData(authViewModel)
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }

    val commentViewModel: CommentViewModel = viewModel()

    var imageUrl: String? by remember { mutableStateOf(null) }
    val imagePickerHandler = rememberImagePicker(
        coroutineScope = coroutineScope,
        onImageUploaded = { url -> imageUrl = url }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(size = 24.dp))
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
                    text = "Answer Question",
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
            Spacer(modifier = Modifier.padding(vertical = 20.dp))

            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .border(2.dp, Color(0xFFFDBA21), RoundedCornerShape(16.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(size = 16.dp))
                    .size(height = 32.dp, width = 111.dp)
                    .clickable {  },
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.plus_subject_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 3.dp),
                    tint = Color.Black
                )

                Text(
                    text = "Subject",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color.Black,
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp),
                )

            }
            TextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(500.dp)
                    .focusRequester(focusRequester),
                value = comment,
                onValueChange = {
                    comment = it
                },
                placeholder = {
                    Text(
                        text = "Answer The Question",

                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = montserratFont,
                            color = Color(0xFF757575),
                        )
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedPlaceholderColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .background(Color(0xFFFDBA21))
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus_icon_add),
                contentDescription = null,
                modifier = Modifier.clickable {  },
                tint = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.camera_icon_add_image),
                contentDescription = null,
                modifier = Modifier.clickable {
                    imagePickerHandler.permissionLauncher.launch(android.Manifest.permission.CAMERA)
                },
                tint = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.image_icon_add_image),
                contentDescription = null,
                modifier = Modifier.clickable {
                    imagePickerHandler.galleryLauncher.launch("image/*")
                },
                tint = Color.White
            )
            Box(
                modifier = Modifier
                    .background(Color(0xFFDAA200), RoundedCornerShape(20.dp))
                    .width(223.dp)
                    .height(38.dp)
                    .fillMaxWidth()
                    .clickable {
                        focusRequester.requestFocus() // Request focus on TextField
                    }
            ){
                Text(
                    text = "Write here",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.6.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF6C7278),
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 20.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.send_icon_send_message),
                contentDescription = null,
                modifier = Modifier.clickable {
                    try {
                        commentViewModel.sendComment(
                            authorProfile = authorProfileData.value,
                            commentContent = comment,
                            threadId = threadId,
                            imageUrl = imageUrl
                        )
                        navController.navigate("comment/$threadId")
                    } catch (e: Exception) {
                        Log.e("AddThreadPage", "Error: ${e.message}")
                    }
                },
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun ThreadCommentAddPreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null
) {
    ThreadCommentAdd(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
        threadId = ""
    )
}