package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import com.example.raionapp.common.nunitoFont

@Composable
fun ChatBubbleMentor(
    mentorMessage : String,
    modifier: Modifier = Modifier,
) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f),
        ) {
            Image(
                painter = painterResource(R.drawable.user_dummy_image),
                contentDescription = null,
                Modifier
                    .size(30.dp)
                    .align(Alignment.Bottom)
            )
            Spacer(Modifier.width(10.dp))
            Box(
                Modifier
                    .wrapContentSize()
                    .background(color = Color(0xFFEEEEEE), shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp, bottomStart = 0.dp, bottomEnd = 25.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = mentorMessage,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF505050),
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChatBubbleMentorPreview() {
    ChatBubbleMentor("Im Fine bruh")
}