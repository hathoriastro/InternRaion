package com.example.raionapp.presentation.homePage.learningPage

import androidx.compose.foundation.Image
import com.example.raionapp.presentation.homePage.NavBar
import com.example.raionapp.presentation.homePage.TopBarAndProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.homePage.model.LearningPageViewModel
import com.example.raionapp.presentation.register.AuthViewModel

@Composable
fun LearningPageHome(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    var search by remember { mutableStateOf("") }

    val learningPageViewModel: LearningPageViewModel = viewModel()
    val lesson = learningPageViewModel.learningState.collectAsState()
    val groupedLesson = lesson.value.groupBy {
        it.second.subject
    }

    Scaffold(
        bottomBar = {
            Surface(
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                NavBar(navController = navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color(0xFF5598CC))
                ){

                }

//        Profile Picture
                val userProfileData = profileData(authViewModel = authViewModel)
                Image(
                    painter = rememberAsyncImagePainter(
                        model = userProfileData.value?.profilePicture,
                        placeholder = painterResource(R.drawable.profile_picture),
                        error = painterResource(R.drawable.profile_picture)
                    ),
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .width(71.dp)
                        .height(69.dp)
                        .align(Alignment.TopEnd)
                        .zIndex(2f)
                        .offset(x = -10.dp, y = 50.dp)
                        .clip(CircleShape)
                        .clickable { navController.navigate("profile") }
                )

                Image(
                    painter = painterResource(id = R.drawable.heading_eclipse),
                    contentDescription = "Header Background",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(x = 110.dp, y = 0.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .offset(x = 15.dp, y = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Hello, " + userProfileData.value?.fullname ?: "....",

                        // Headline/H5 Headline Bold
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )

                    Text(
                        text = "Find best course for you!",

                        // Headline/H1 Head Bold
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )

                    Text(
                        text = "We have more than 60+ courses",

                        // Body Text/Body Small Medium
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }


            }
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .fillMaxHeight(0.75f)
                    .align(Alignment.BottomCenter)
                    .zIndex(0f)
                    .fillMaxWidth()
            ){
                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .width(290.dp)
                        .height(60.dp)
                        .offset(y = -30.dp)
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp, // Border width
                            color = Color(0xFF1A5294), // Border color
                            shape = RoundedCornerShape(10.dp) // Match the TextField's shape
                        )
                        .padding(4.dp) // Add padding to avoid overlap with the border
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 13.dp)
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(60.dp)
                            .zIndex(4f),
                        value = search,
                        onValueChange = { search = it },
                        shape = RoundedCornerShape(10.dp),
                        placeholder = {
                            Text(
                                text = "Search",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = montserratFont,
                                    color = Color(0xFF757575),
                                ),
                                modifier = Modifier.fillMaxHeight()
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                        )
                        .fillMaxSize()
                        .padding(top = 30.dp, start = 16.dp)
                        .align(Alignment.BottomCenter)
                        .zIndex(0f)
                        .verticalScroll(rememberScrollState()),
                ) {
                    // Tampilkan disini dengan loop berdasarkan jumlah subject yang tersedia
                    groupedLesson.forEach {(subjectName, lessonsData) ->
                        LearningContentRow(
                            subjectName = subjectName,
                            lessonData = lessonsData,
                            navController = navController
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun LearningPageHomePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    LearningPageHome(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
    )
}
