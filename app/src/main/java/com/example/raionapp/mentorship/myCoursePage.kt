package com.example.raionapp.mentorship

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.raionapp.presentation.homePage.learningPage.LearningContentRow
import com.example.raionapp.presentation.homePage.model.profileData
import com.example.raionapp.presentation.register.AuthViewModel

@Composable
fun MyCoursePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    val userId = authViewModel?.auth?.currentUser?.uid
    val myCourseViewModel: MyCoursePageViewModel = viewModel()
    val myCourse = myCourseViewModel.myCourseState.collectAsState()
    val groupedLesson = myCourse.value.groupBy { (_, lessonData) ->
        lessonData.subject
    }

    LaunchedEffect(userId) {
        userId?.let {
            myCourseViewModel.loadMentorClass(userId)
        }
    }

    Scaffold(
        bottomBar = {
            Surface(
                color = Color.White,
                shadowElevation = 8.dp
            ) {
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("addnewclass")
                },
                containerColor = Color(0xFF1A5294),
                shape = RoundedCornerShape(30.dp),
                contentColor = Color.White,
                modifier = Modifier.offset(y = -10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                )
            }
        },
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
                        .height(200.dp)
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

                Row(
                    modifier = Modifier.offset(x = 10.dp, y = 90.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.clickable { navController.navigate("profile") }
                    )

                    Text(
                        text = "My Course ",
                        // Headline/H5 Headline Bold
                        style = TextStyle(
                            fontSize = 26.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }


            }

            // Isi Konten Utama
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .fillMaxHeight(0.84f)
                    .align(Alignment.BottomCenter)
                    .zIndex(0f)
                    .fillMaxWidth()
            ){
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
                    if (groupedLesson.isEmpty()) {
                        Text(
                            text = "Anda belum memiliki satupun kelas",
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        )
                    } else {
                        groupedLesson.forEach { (subjectName, lessonsData) ->
                            LearningContentRow(
                                subjectName = subjectName,
                                lessonData = lessonsData,
                                navController = navController
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun MyCoursePagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    MyCoursePage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
    )
}
