package com.example.raionapp.presentation.homePage

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.model.ThreadViewModel
import com.example.raionapp.presentation.homePage.threads.Thread

@Composable
fun HomePageScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
) {

    val threadViewModel: ThreadViewModel = viewModel()
    val thread = threadViewModel.threadsState.collectAsState()

    Scaffold(
        floatingActionButton = {
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
        },
        bottomBar = {
            Surface(
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                NavBar(navController = navController)
            }
        }
    ){ paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .zIndex(4f)
                        .offset(y = 15.dp)
                        .border(width = 1.dp, color = Color(0xFFF5F6F9), shape = RoundedCornerShape(size = 7.dp))
                        .width(332.dp)
                        .height(46.dp)
                        .background(color = Color(0xFFF5F6F9), shape = RoundedCornerShape(size = 7.dp))
                        .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                ) {
                    Row( //TOMBOL UNTUK MEMILIH MATKUL
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width(161.dp)
                            .fillMaxHeight()
                            .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                            .background(Color.White, RoundedCornerShape(7.dp))
                            .clickable { navController.navigate("subjectselectpage") }
                    ) {
                        Text(
                            text = "STATISTIKA",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF232447),
                            ),
                            modifier =Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width(161.dp)
                            .fillMaxHeight()
                            .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                            .background(Color.White, RoundedCornerShape(7.dp))
                            .clickable {
                                navController.navigate("semesterselectpage")
                            }
                    ) {
                        Text(
                            text = "SEMESTER 3",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF232447),
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                        )
                        .zIndex(2f)
                        .fillMaxSize()
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .verticalScroll(rememberScrollState()),
                ) {
                    thread.value.forEach { (threadId, threadData) ->
                        ThreadContent(
                            fullname = threadData.fullname,
                            username = threadData.username,
                            profilePicture = threadData.authorProfilePicture,
                            text = threadData.threadText,
                            numberOfComment = threadData.numberOfComment,
                            numberOfLike = threadData.numberOfLike,
                            threadId = threadId,
                            isLiked = threadData.isLiked,
                            navController = navController
                        )
                    }
//                ContentScreen("Jamal", "udinpetot")
//
//                ContentScreen("Joko", "jokoganteng123")
//
//                ContentScreen("Tirta", "doktertirta")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePageScreenPreview() {
    HomePageScreen(
        modifier = Modifier,
        navController = rememberNavController(),
        authViewModel = null
    )
}