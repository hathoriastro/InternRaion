package com.example.raionapp.presentation.homePage

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.presentation.authentication.AuthViewModel

@Composable
fun HomePageScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
    context: Context
) {
    val thread = threadDataSync()
    val coroutineScope = rememberCoroutineScope()

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
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .height(640.dp)
                    .align(Alignment.BottomCenter)
                    .zIndex(2f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                thread.forEach { (threadId, threadData) ->
                    ContentScreen(
                        fullname = threadData.fullname,
                        username = threadData.username,
                        profilePicture = threadData.authorProfilePicture,
                        text = threadData.threadText,
                        numberOfComment = threadData.numberOfComment,
                        numberOfLike = threadData.numberOfLike,
                        coroutineScope = coroutineScope,
                        threadId = threadId
                    )
                }
                Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(150.dp))
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
                    thread.forEach { (threadId, threadData) ->
                        Thread(
                            fullname = threadData.fullname,
                            username = threadData.username,
                            profilePicture = threadData.authorProfilePicture,
                            text = threadData.threadText,
                            numberOfComment = threadData.numberOfComment,
                            numberOfLike = threadData.numberOfLike,
                            coroutineScope = coroutineScope
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
fun HomePageScreenPreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
    context: Context
) {
    HomePageScreen(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
        context = context
    )
}