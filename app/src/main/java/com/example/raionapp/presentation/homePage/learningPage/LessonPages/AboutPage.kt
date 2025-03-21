package com.example.raionapp.presentation.learningPage.learningPageHome

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun AboutPage(
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
                            .clickable { navController.navigate("lessonpageunlocked") }
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
                                        text = "By Robert James",
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
                                text = "Lorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a. Read More",
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
                                    text = "60 Lessons",
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
                                    text = "20 hr 45 min",
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
                                    text = "English",
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
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(size = 16.dp)
                            )
                            .border(2.dp, color = Color(0xFFFDCB1A), RoundedCornerShape(16.dp))
                            .padding(30.dp)
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Text(
                                text = "Private 1 on 1",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(400),
                                    color = Color.Gray,
                                )
                            )

                            Text(
                                text = "Rp100.000,-",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                        }
                        Box(
                            Modifier
                                .align(Alignment.CenterEnd)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF5598CC),
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .width(87.dp)
                                .height(32.dp)
                                .clickable {
                                    navController.navigate("paymentoverview")
                                }
                        ) {
                            Text(
                                text = "Buy",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF1A1A1A),
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier.align(Alignment.Center)
                            )

                        }
                    }

                    Box(
                        Modifier
                            .padding(vertical = 20.dp)
                            .width(308.dp)
                            .height(110.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(size = 16.dp)
                            )
                            .border(2.dp, color = Color(0xFFFDCB1A), RoundedCornerShape(16.dp))
                            .padding(30.dp)
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Text(
                                text = "General Class",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(400),
                                    color = Color.Gray,
                                )
                            )

                            Text(
                                text = "Rp150.000,-",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                        }
                        Box(
                            Modifier
                                .align(Alignment.CenterEnd)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF5598CC),
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .width(87.dp)
                                .height(32.dp)
                                .clickable {
                                    navController.navigate("paymentoverview")
                                }
                        ) {
                            Text(
                                text = "Buy",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF1A1A1A),
                                    textAlign = TextAlign.Center,
                                ),
                                modifier = Modifier.align(Alignment.Center)
                            )

                        }

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
    )
}
