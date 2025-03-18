package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont

@Composable
fun LessonPageUnlockedMaterial(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .height(32.dp)
                .wrapContentWidth()
                .border(1.dp, Color(0xFF1A5294), RoundedCornerShape(16.dp))
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            Text(
                text = "Introduction To Website Design",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                ),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 15.dp)
            )
        }

        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = "Pokok Bahasan :",
                style = TextStyle(
                    fontSize = 13.sp,
                    lineHeight = 21.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                )
            )

            Text(
                text = "1. Definisi dan tujuan website \n2. Jenis-jenis website (statis, dinamis, e-commerce, dll.)",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 13.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier.wrapContentSize()
            )
        }
        Box(
            Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF1A5294),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(1.dp)
                .fillMaxWidth()
                .height(92.dp)
                .background(color = Color(0x00D9D9D9), shape = RoundedCornerShape(size = 16.dp))
                .clickable {

                }
        ){
            Box(
                Modifier
                    .padding(horizontal = 10.dp)
                    .width(68.dp)
                    .height(63.dp)
                    .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(size = 8.dp))
                    .align(Alignment.CenterStart)
            ){
                Icon(
                    painter = painterResource(R.drawable.file_icon_sublesson),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "FILE",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF757575),
                    )
                )
                Text(
                    text = "Introduction To Website Design",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }
        }

        Box(
            Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF1A5294),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(1.dp)
                .fillMaxWidth()
                .height(92.dp)
                .background(color = Color(0x00D9D9D9), shape = RoundedCornerShape(size = 16.dp))
                .clickable {
                    navController.navigate("videopage")
                }
        ){
            Box(
                Modifier
                    .padding(horizontal = 10.dp)
                    .width(68.dp)
                    .height(63.dp)
                    .background(color = Color(0xFF5598CC), shape = RoundedCornerShape(size = 8.dp))
                    .align(Alignment.CenterStart)
            ){
                Icon(
                    painter = painterResource(R.drawable.video_icon_sublesson),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "VIDEO",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF757575),
                    )
                )
                Text(
                    text = "Introduction To Website Design",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }
        }

        Box(
            Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFF1A5294),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(1.dp)
                .fillMaxWidth()
                .height(92.dp)
                .background(color = Color(0x00D9D9D9), shape = RoundedCornerShape(size = 16.dp))
                .clickable {
                    navController.navigate("videopage")
                }
        ){
            Box(
                Modifier
                    .padding(horizontal = 10.dp)
                    .width(68.dp)
                    .height(63.dp)
                    .background(color = Color(0xFFFDBA21), shape = RoundedCornerShape(size = 8.dp))
                    .align(Alignment.CenterStart)
            ){
                Icon(
                    painter = painterResource(R.drawable.video_practice_icon_sublesson),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "VIDEO PRACTICE",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF757575),
                    )
                )
                Text(
                    text = "Introduction To Website Design",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun LessonPageMaterialPreview() {
    LessonPageUnlockedMaterial(
        navController = rememberNavController()
    )
}