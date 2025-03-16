package com.example.raionapp.presentation.homePage.learningPage.LessonPages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont

@Composable
fun ReviewPageBox(modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(132.dp)
            .background(
                color = Color(0xFF5598CC),
                shape = RoundedCornerShape(size = 10.dp)
            ),
    ) {
        Text(
            text = "6 months ago",
            style = TextStyle(
                fontSize = 10.sp,
                lineHeight = 15.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(400),
                color = Color(0xFFF5F6F9),
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = 10.dp, x = -10.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(id = R.drawable.reviewer_example_png),
                    contentDescription = null,
                    modifier = Modifier
                        .width(42.dp)
                        .height(42.dp)
                        .align(Alignment.CenterVertically)
                        .padding(5.dp)
                )

                Column(

                ){
                    Text(
                        text = "Olivia Tom",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 21.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF000000),
                        )
                    )
                    Text(
                        text = "United States",
                        style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFD9D9D9),
                        )
                    )
                }
            }
            Text(
                text = "Lorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. ",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 18.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF5F6F9),
                )
            )
            Image(
                painter = painterResource(R.drawable._5_star),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun ReviewPageBoxPreview() {
    ReviewPageBox()
}