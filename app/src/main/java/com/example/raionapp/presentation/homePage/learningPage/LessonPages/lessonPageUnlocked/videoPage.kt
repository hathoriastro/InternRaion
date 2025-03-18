package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.exoPlayer.Media3PlayerView
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.TopBarAndProfile
import com.example.raionapp.presentation.homePage.threads.ThreadContent

@Composable
fun VideoPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel?
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
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
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            ) {
                Row(
                    modifier = Modifier
                        .offset(20.dp, 20.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Back",

                        // Body Text/Body 1 Medium
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF000000),
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.92f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {

                    Spacer(modifier = Modifier.height(35.dp))
                    Media3PlayerView(videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",)

                    Column(
                        modifier = Modifier
                            .fillMaxSize(0.9f),
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ){
                        Column {
                            Text(
                                text = "Website Design",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.mentor_icon_human),
                                    contentDescription = null,
                                    tint = Color.Gray
                                )

                                Text(
                                    text = "By Robert James",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(400),
                                        color = Color.Gray,
                                    ),
                                    modifier = Modifier.padding(start = 7.dp)
                                )
                            }
                        }
                        Text(
                            text = "Lorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a. \n\nLorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )

                        Text(
                            text = "Lorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a. \n\nLorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )

                        Text(
                            text = "Lorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a. \n\nLorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
private fun VideoPagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    VideoPage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
    )

}

