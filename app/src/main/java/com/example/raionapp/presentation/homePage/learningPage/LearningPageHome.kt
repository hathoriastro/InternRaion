package com.example.raionapp.presentation.homePage.learningPage

import com.example.raionapp.presentation.homePage.NavBar
import com.example.raionapp.presentation.homePage.TopBarAndProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.homePage.model.LearningPageViewModel
import com.example.raionapp.presentation.homePage.model.profileData
import com.example.raionapp.presentation.register.AuthViewModel

@Composable
fun LearningPageHome(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    var search by remember { mutableStateOf("") }
    val learningPageViewModel: LearningPageViewModel = viewModel()
    val userProfileData = profileData(authViewModel = authViewModel)
    val userRole = userProfileData.value?.role

    LaunchedEffect(userRole) {
        learningPageViewModel.loadLesson(userRole)
    }

    Scaffold(
        floatingActionButton = {
            if (userRole == "mentor"){
                FloatingActionButton(
                    onClick = { navController.navigate("addthread") },
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
            }
        },
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
            TopBarAndProfile(
                modifier = modifier,
                navController = navController,
                authViewModel = authViewModel,
            )
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .fillMaxHeight(0.77f)
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
                    Row {
                        Text(
                            text = "Kedokteran",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 27.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF212121),
                            ),
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        LearningContent(
                            "Kedokteran",
                            "Biologi",
                            "Jamal Michael",
                            1000,
                            navController = navController,
                            authViewModel = authViewModel
                            )
                    }

                    Row {
                        Text(
                            text = "Ilmu Komputer",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 27.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF212121),
                            ),
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        LearningContent(
                            "Ilmu Komputer",
                            "Pemrograman Dasar",
                            "Jamal Michael",
                            1000,
                            navController = navController,
                            authViewModel = authViewModel
                        )

                        LearningContent(
                            "Ilmu Komputer",
                            "Algoritma",
                            "Ambudianto",
                            1000,
                            navController = navController,
                            authViewModel = authViewModel
                        )

                        LearningContent(
                            "Ilmu Komputer",
                            "Sistem Digital",
                            "John Damkar",
                            1000,
                            navController = navController,
                            authViewModel = authViewModel
                        )
                    }

                    Row {
                        Text(
                            text = "Kedokteran",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 27.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF212121),
                            ),
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        LearningContent(
                            "Kedokteran",
                            "Biologi",
                            "Jamal Michael",
                            1000,
                            navController = navController,
                            authViewModel = authViewModel
                        )

                        LearningContent(
                            "Kedokteran",
                            "Biologi",
                            "Jamal Michael",
                            1000,
                            navController = navController,
                            authViewModel = authViewModel
                        )

                        LearningContent(
                            "Kedokteran",
                            "Biologi",
                            "Jamal Michael",
                            1000,
                            navController = navController,
                            authViewModel = authViewModel
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
