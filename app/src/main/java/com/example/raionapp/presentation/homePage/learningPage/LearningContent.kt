package com.example.raionapp.presentation.homePage.learningPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.raionapp.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.raionapp.common.montserratFont

@Composable
fun LearningContent(
    lessonId: String,
    subjectName : String,
    lessonName: String,
    mentorName : String,
    mentorProfilePicture: String?,
    likeCount : Int,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Column(
        modifier = modifier
            .width(242.dp)
            .height(213.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 5.dp))
            .border(1.dp, color = Color(0xFF1A5294), RoundedCornerShape(5.dp))
            .clickable {
                navController.navigate("aboutpage/$lessonId")
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_example_kedokteran_1),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .width(224.dp)
                .height(126.dp)
                .align(Alignment.CenterHorizontally)
                .padding(start = 0.dp, top = 9.dp, end = 0.dp)
                .clip(RoundedCornerShape(5.dp))
        )

        Text(
            text = subjectName,
            style = TextStyle(
                fontSize = 9.sp,
                lineHeight = 13.5.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(400),
                color = Color.Black,
                letterSpacing = 0.05.em,
            ),
            modifier = modifier
                .padding(start = 9.dp, top = 9.dp)
        )

        Text(
            text = lessonName,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 21.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(500),
                color = Color.Black,
                letterSpacing = 0.05.em,
            ),
            modifier = modifier
                .padding(start = 9.dp, top = 3.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(start = 9.dp, top = 6.dp)
        ){
            Image(
                painter = rememberAsyncImagePainter(
                    model = mentorProfilePicture,
                    placeholder = painterResource(R.drawable.profile_icon_unclicked),
                    error = painterResource(R.drawable.profile_icon_unclicked)
                ),

                contentDescription = null,
                modifier = modifier
                    .width(22.dp)
                    .height(21.dp)
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
            )

            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .width(75.dp)
                    .padding(start = 9.dp)
            ){
                Text(
                    text = mentorName,
                    style = TextStyle(
                        fontSize = 9.sp,
                        lineHeight = 13.5.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(500),
                        color = Color.Black,
                        letterSpacing = 0.05.em,
                    )
                )
                Text(
                    text = likeCount.toString(),
                    style = TextStyle(
                        fontSize = 6.sp,
                        lineHeight = 9.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF757575),
                    )
                )
            }
            Box(
                modifier = modifier
                    .padding(start = 80.dp, top = 3.dp, end = 6.dp, bottom = 3.dp)
                    .height(18.dp)
                    .align(Alignment.CenterVertically)
                    .width(41.dp)
                    .background(color = Color(0xFFFDCB1A), shape = RoundedCornerShape(size = 5.dp))
            ){
                Text(
                    text = "Private",
                    style = TextStyle(
                        fontSize = 8.sp,
                        lineHeight = 12.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                    ),
                    modifier = modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
private fun LearningContentPreview(
    navController: NavHostController = rememberNavController(),
) {
    LearningContent(
        lessonId = "",
        subjectName = "Kedokteran",
        lessonName = "Anatomi",
        mentorName = "Niken Fardani",
        likeCount = 1000,
        mentorProfilePicture = "",
        navController = navController,
    )
}