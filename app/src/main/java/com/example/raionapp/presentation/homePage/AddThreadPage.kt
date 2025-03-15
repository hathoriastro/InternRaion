package com.example.raionapp.presentation.homePage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionapp.R
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.Firestore.Model.ProfileDataClass
import com.example.raionapp.Firestore.Model.ThreadDataClass
import com.example.raionapp.Firestore.ProfileCollection
import com.example.raionapp.Firestore.ThreadCollection
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.profile.profileData
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddThreadPage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    var thread by remember { mutableStateOf("") }

//  Kirim Thread ke Firestore
    val authorProfileData = profileData(authViewModel)
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(size = 24.dp))
            ){
                Image(
                    painter = painterResource(id = R.drawable.left_arrow_icon),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 40.dp, top = 20.dp)
                        .clickable { navController.popBackStack() },
                )

                Text(
                    text = "Ask Questions",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 60.dp, top = 20.dp)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 20.dp))

            Row(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .background(color = Color(0xFFFDBA21), shape = RoundedCornerShape(size = 16.dp))
                    .size(height = 32.dp, width = 111.dp)
                    .clickable {
                        try {
                            sendThread(
                                authorProfile = authorProfileData.value,
                                threadContent = thread,
                                coroutineScope = coroutineScope
                            )
                            navController.navigate("home")
                        } catch (e: Exception) {
                            Log.e("AddThreadPage", "Error: ${e.message}")
                        }
                    },
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.plus_subject_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 3.dp),
                    tint = Color.White
                )

                Text(
                    text = "Subject",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp),
                )

            }
            TextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(500.dp),
                value = thread,
                onValueChange = {
                    thread = it
                },
                placeholder = {
                    Text(
                        text = "What do you need to know?",

                        // Body Text/Body Small Medium
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
    }
}

private fun sendThread(
    authorProfile: ProfileDataClass?,
    threadContent: String = "",
    coroutineScope: CoroutineScope
) {
    var questionCount = authorProfile?.numberOfQuestion
    coroutineScope.launch {
        val thread = ThreadDataClass(
            userId = authorProfile?.userId.orEmpty(),
            fullname = authorProfile?.fullname.orEmpty(),
            username = authorProfile?.username.orEmpty(),
            threadText = threadContent,
            authorProfilePicture = authorProfile?.profilePicture.orEmpty(),
            numberOfLike = 0,
            numberOfComment = 0
        )
        ThreadCollection().addThreadToFirestore(thread)

        val updateNumberOfQuestion = mapOf("numberOfQuestion" to (questionCount?.plus(1)))
        ProfileCollection().updateProfileInFirestore(
            authorProfile?.userId.toString(),
            updateNumberOfQuestion
        )
    }
}

@Preview
@Composable
private fun AddThreadPageReview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null
) {
    AddThreadPage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel
    )
}