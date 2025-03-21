package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.homePage.TopBarAndProfile
import com.example.raionapp.presentation.homePage.model.LearningPageViewModel
import com.example.raionapp.presentation.homePage.threads.ThreadContent
import com.example.raionapp.presentation.register.AuthViewModel

@Composable
fun ModulListPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel?,
    lessonId: String
) {
    val learningPageViewModel: LearningPageViewModel = viewModel()
    val subLesson = learningPageViewModel.subLessonState.collectAsState()

    LaunchedEffect(lessonId) {
        learningPageViewModel.loadSubLesson(lessonId)
    }

    var search by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        TopBarAndProfile(
            modifier = modifier,
            navController = navController,
            authViewModel = authViewModel,
        )
        Column(
            Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .fillMaxSize()
                    .background(color = Color.White, shape = RoundedCornerShape(30.dp))
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(290.dp)
                            .height(60.dp)
                            .background(Color.White, shape = RoundedCornerShape(20.dp))
                            .border(
                                width = 1.dp, // Border width
                                color = Color(0xFF1A5294), // Border color
                                shape = RoundedCornerShape(20.dp) // Match the TextField's shape
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

                    HorizontalDivider(
                        thickness = 3.dp,
                        modifier = Modifier.fillMaxWidth(0.98f)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .height(40.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        Icon(
                            painter = painterResource(R.drawable.arrow_back_button),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    navController.popBackStack()
                                }
                        )
                        Box(modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFFFDBA21), CircleShape),
                            contentAlignment = Alignment.Center,
                        ){
                            Icon(
                                painter = painterResource(R.drawable.back_button_reader_icon),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Text(
                            text = "Modul",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(600),
                                color = Color(0xBF000000),
                            )
                        )

                    }
                    HorizontalDivider(
                        thickness = 3.dp,
                        modifier = Modifier.fillMaxWidth(0.98f)
                    )

                    Box(
                        Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Column(
                            Modifier
                                .padding(20.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(15.dp)

                        ) {
                            subLesson.value.forEach {subLessonData ->
                                ModulListPageContent(
                                    contentTitle = subLessonData.sublessonName,
                                    fileUrl = subLessonData.fileUrl,
                                    navController = navController
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
private fun ModulListPagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    ModulListPage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
        lessonId = ""
    )

}

