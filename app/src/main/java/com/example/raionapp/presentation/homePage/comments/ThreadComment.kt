package com.example.raionapp.presentation.homePage.comments

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.NavBar
import com.example.raionapp.presentation.homePage.TopBarAndProfile
import com.example.raionapp.presentation.homePage.model.CommentViewModel
import com.example.raionapp.presentation.homePage.threads.Thread
import kotlin.concurrent.thread
import com.example.raionapp.R
import com.example.raionapp.presentation.homePage.model.ThreadViewModel

@Composable
fun ThreadComment(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
    threadId: String
) {
    val commentViewModel: CommentViewModel = viewModel()

    LaunchedEffect(threadId) {
        commentViewModel.loadComments(threadId)
    }
    val commentModel = commentViewModel.commentState.observeAsState()
    val threadViewModel: ThreadViewModel = viewModel()
    val thread = threadViewModel.threadsState.observeAsState()
    val selectedThread = thread.value?.firstOrNull { it.first == threadId }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addcomment/$threadId") },
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
                    selectedThread?.let { (threadId, threadData) ->
                        // Tampilkan detail thread yang dipilih
                        Thread(
                            threadId = threadId,
                            fullname = threadData.fullname,
                            username = threadData.username,
                            profilePicture = threadData.authorProfilePicture,
                            text = threadData.threadText,
                            numberOfComment = threadData.numberOfComment,
                            numberOfLike = threadData.numberOfLike,
                            navController = navController,
                            modifier = Modifier
                        )
                    }
                    commentModel.value?.forEach { (threadId, commentId, commentData) ->
                        ThreadCommentSub(
                            threadId = threadId,
                            fullname = commentData.fullname,
                            username = commentData.username,
                            profilePicture = commentData.profilePicture,
                            text = commentData.text,
                            numberOfLike = commentData.numberOfLike,
                            commentId = commentId,
                            navController = navController
                            )
                    }
                }
            }
        }
    }
}