package com.example.raionapp.presentation.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.threads.ThreadContent
import com.example.raionapp.presentation.homePage.model.ThreadViewModel

@Composable
fun HomePageScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
) {

    val threadViewModel: ThreadViewModel = viewModel()
    val thread = threadViewModel.threadsState.observeAsState()

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
                    thread.value?.forEach { (threadId, threadData) ->
                        ThreadContent(
                            fullname = threadData.fullname,
                            username = threadData.username,
                            profilePicture = threadData.authorProfilePicture,
                            text = threadData.threadText,
                            numberOfComment = threadData.numberOfComment,
                            numberOfLike = threadData.numberOfLike,
                            threadId = threadId
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