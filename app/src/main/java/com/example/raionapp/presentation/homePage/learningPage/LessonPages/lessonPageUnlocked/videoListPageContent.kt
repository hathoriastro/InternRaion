package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.raionapp.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.raionapp.common.montserratFont
import java.net.URLEncoder

@Composable
fun VideoListPageContent(
    contentTitle: String,
    link: String,
    navController: NavController
) {
    
    Column(
        Modifier
            .shadow(8.dp, RoundedCornerShape(15.dp))
            .background(color = Color.White, shape = RoundedCornerShape(size = 15.dp))
            .width(500.dp)
            .wrapContentHeight()
            .background(color = Color.White, shape = RoundedCornerShape(size = 15.dp))
    ) {
        Box(
            Modifier
                .height(100.dp)
                .clickable {

                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.bank_page_dummy_png),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
            )
            Icon(
                painter = painterResource(id = R.drawable.play_circle_icon),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(30.dp),
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(Color.White)
                .clickable {
                    navController.navigate("videopage/${URLEncoder.encode(link, "UTF-8")}")
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = contentTitle,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(600),
                    color = Color(0xBF000000),
                ),
                modifier = Modifier.padding(10.dp)
            )
            Column(

            ) {
                Box(
                    Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFFD1D1D1),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                        .width(331.dp)
                        .height(10.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 5.dp)
                        )
                ){
                    Box(
                        Modifier
                            .fillMaxWidth(0.35f)
                            .height(8.dp)
                            .background(color = Color(0xFFFDBA21), shape = RoundedCornerShape(size = 5.dp))
                    ){

                    }
                }
                Text(
                    text = "35% Selesai",
                    style = TextStyle(
                        fontSize = 9.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF757575),
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.Start)
                )
            }
        }

    }
}

@Preview
@Composable
private fun VideoListPageContentPreview() {
    VideoListPageContent(
        contentTitle = "",
        link = "",
        navController = rememberNavController()
        )
}