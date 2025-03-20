package com.example.raionapp.presentation.homePage.learningPage.LessonPages

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.homePage.model.LearningPageViewModel
import com.example.raionapp.presentation.register.AuthViewModel

@Composable
fun AboutPage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
    lessonId: String
) {
    var search by remember { mutableStateOf("") }

    //  Memeriksa keanggotaan pengguna
    val userId = authViewModel?.auth?.currentUser?.uid
    val learningPageViewModel: LearningPageViewModel = viewModel()
    val lessonDetail by learningPageViewModel.lessonDetailsState.collectAsState()

    LaunchedEffect(lessonId) {
        learningPageViewModel.loadLessonDetails(lessonId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.arrow_back_button),
            contentDescription = null,
            modifier = Modifier
                .zIndex(2f)
                .offset(y = 60.dp, x = 30.dp)
                .clickable {
                    navController.navigate("learningpage")
                }
        )

        Image(
            painter = painterResource(R.drawable.heading_background_jpg),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.312f),
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
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .offset(y = -20.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFF5F6F9),
                        shape = RoundedCornerShape(size = 7.dp)
                    )
                    .width(332.dp)
                    .height(46.dp)
                    .background(
                        color = Color(0xFFF5F6F9),
                        shape = RoundedCornerShape(size = 7.dp)
                    )
                    .align(Alignment.TopCenter)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(3.dp)
                        .background(color = Color(0xFFFDCB1A), RoundedCornerShape(6.dp))
                        .fillMaxWidth(0.34f)
                        .fillMaxHeight()
                        .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                        .clickable { }
                ) {
                    Text(
                        text = "ABOUT",
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

                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(3.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(size = 6.dp)
                        )
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                        .clickable {
                            val destination = if (learningPageViewModel.checkClassMembership(userId)) {
                                "lessonpageunlocked/$lessonId"
                            } else {
                                "lessonpage/$lessonId"
                            }
                            navController.navigate(destination)
                        }
                ) {
                    Text(
                        text = "LESSONS",
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

                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(3.dp)
                        .background(Color.White, RoundedCornerShape(6.dp))
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                        .clickable {
                            navController.navigate("reviewpage/$lessonId")
                        }
                ) {
                    Text(
                        text = "REVIEWS",
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
                        color = Color.Transparent,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
                    .zIndex(0f)
                    .padding(top = 30.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(size = 10.dp)
                        ),
                ) {
                    Row(
                        modifier = Modifier
                            .offset(y = 20.dp, x = -20.dp)
                            .align(Alignment.TopEnd)
                            .wrapContentHeight()
                            .wrapContentWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable._5_star),
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Text(
                            text = "4.9",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 15.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(700),
                                color = Color.Gray,
                            ),
                            modifier = Modifier
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = lessonDetail?.lessonName.toString(),
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 30.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF1E1E1E),
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.mentor_icon_human),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                                    .align(Alignment.CenterVertically)
                                    .padding(5.dp)
                            )

                            Column(

                            ) {
                                Text(
                                    text = "By ${lessonDetail?.mentorName.toString()}",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(400),
                                        color = Color.Gray,
                                    )
                                )
                            }
                        }
                        Text(
                            text = lessonDetail?.about.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                }
                Box(
                    Modifier
                        .padding(vertical = 20.dp)
                        .width(308.dp)
                        .height(89.dp)
                        .background(
                            color = Color(0xFF1A5294),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 30.dp, top = 15.dp, bottom = 15.dp, end = 40.dp)
                            .fillMaxSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.book_icon_aboutpage),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "${lessonDetail?.numberOfSublesson.toString()} Lessons",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFF5F6F9),
                                )
                            )
                        }

                        Row(
                            modifier = Modifier.align(Alignment.BottomStart),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.time_icon_aboutpage),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = lessonDetail?.duration.toString(),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFF5F6F9),
                                )
                            )
                        }

                        Row(
                            modifier = Modifier.align(Alignment.BottomEnd),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.certificate_icon_aboutpage),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "Certificate",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFF5F6F9),
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .fillMaxWidth(0.38f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.language_icon_aboutpage),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = lessonDetail?.language.toString(),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFF5F6F9),
                                )
                            )
                        }


                    }

                }
                Box(
                    Modifier
                        .padding(vertical = 20.dp)
                        .width(308.dp)
                        .height(110.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(size = 16.dp))
                        .border(2.dp, color = Color(0xFFFDCB1A), RoundedCornerShape(16.dp))
                ) {

                }

                Box(
                    Modifier
                        .padding(vertical = 20.dp)
                        .width(308.dp)
                        .height(110.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(size = 16.dp))
                        .border(2.dp, color = Color(0xFFFDCB1A), RoundedCornerShape(16.dp))
                ) {

                }

                Box(
                    Modifier
                        .padding(vertical = 20.dp)
                        .width(308.dp)
                        .height(89.dp)
                        .background(
                            color = Color(0xFF5598CC),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                ) {

                }
            }

        }

    }
}

@Preview
@Composable
fun AboutPagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    AboutPage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
        lessonId = ""
    )
}
