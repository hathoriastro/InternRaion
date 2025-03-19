package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionapp.common.nunitoFont

@Composable
fun ChatBubbleUser(
    userMessage : String,
    modifier: Modifier = Modifier,
) {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                Modifier
                    .wrapContentSize()
                    .background(
                        color = Color(0xFF1A5294),
                        shape = RoundedCornerShape(
                            topStart = 25.dp,
                            topEnd = 0.dp,
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = userMessage,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = nunitoFont,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChatBubblePreview() {
    ChatBubbleUser("Hello bang")
}