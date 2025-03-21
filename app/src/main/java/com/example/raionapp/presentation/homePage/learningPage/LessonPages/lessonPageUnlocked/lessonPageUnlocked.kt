package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.authentication.AuthViewModel

@Composable
fun LessonPageUnlocked(
    mentorname: String,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    var search by remember { mutableStateOf("") }
    val commentcount = 10
    val likecount = 0
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                containerColor = Color(0xFF1A5294),
                shape = RoundedCornerShape(30.dp),
                contentColor = Color.White,
                modifier = Modifier.offset(y = -10.dp)
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.about_page_edit),
                    contentDescription = null,
                )
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
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
                    .fillMaxHeight(0.312f)
                    .clipToBounds()
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
                            .background(Color.White, RoundedCornerShape(6.dp))
                            .fillMaxWidth(0.34f)
                            .fillMaxHeight()
                            .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                            .clickable { navController.navigate("aboutpage") }
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
                                color = Color(0xFFFDCB1A),
                                shape = RoundedCornerShape(size = 6.dp)
                            )
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                            .clickable { }
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
                                navController.navigate("reviewpage")
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
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 5.dp)
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mentor_icon_human),
                            contentDescription = null,
                            tint = Color(0xFF565656)
                        )

                        Text(
                            text = mentorname,
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color.Gray,
                            )
                        )

                        Icon(
                            painter = painterResource(R.drawable.lesson_icon_book),
                            contentDescription = null,
                            tint = Color(0xFF565656)
                        )

                        Text(
                            text = "60 Lessons",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color.Gray,
                            )
                        )

                        Icon(
                            painter = painterResource(R.drawable.time_icon_clock),
                            contentDescription = null,
                            tint = Color(0xFF565656)
                        )

                        Text(
                            text = "20hrs 45mins",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color.Gray,
                            )
                        )

                    }
                    Row(
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 10.dp)
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .height(32.dp)
                                .width(75.dp)
                                .border(1.dp, Color(0xFFFDCB1A), RoundedCornerShape(16.dp))
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .clickable {
                                    navController.navigate("modullistpage")
                                }
                        ) {
                            Text(
                                text = "Modul",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color.Black,
                                ),
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )

                        }

                        Box(
                            modifier = Modifier
                                .height(32.dp)
                                .width(75.dp)
                                .border(1.dp, Color(0xFFFDCB1A), RoundedCornerShape(16.dp))
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .clickable { navController.navigate("videolistpage") }
                        ) {
                            Text(
                                text = "Video",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color.Black,
                                ),
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )

                        }

                        Box(
                            modifier = Modifier
                                .height(32.dp)
                                .width(75.dp)
                                .border(1.dp, Color(0xFFFDCB1A), RoundedCornerShape(16.dp))
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .clickable {
                                    navController.navigate("videopracticelistpage")
                                }
                        ) {
                            Text(
                                text = "Praktik",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color.Black,
                                ),
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )

                        }

                        Box(
                            modifier = Modifier
                                .height(32.dp)
                                .width(75.dp)
                                .border(1.dp, Color(0xFFFDCB1A), RoundedCornerShape(16.dp))
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .clickable {
                                    navController.navigate("chatpage")
                                }
                        ) {
                            Text(
                                text = "Diskusi",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color.Black,
                                ),
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )

                        }

                    }

                    Box(

                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(30.dp),
                        ) {
                            LessonPageUnlockedMaterial(navController = navController)
                            LessonPageUnlockedMaterial(navController = navController)
                            LessonPageUnlockedMaterial(navController = navController)

                        }
                    }
                }

            }

        }
    }
}


@Preview
@Composable
fun LessonPagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    LessonPageUnlocked(
        mentorname = "Robert James",
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
    )
}
