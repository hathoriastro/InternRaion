package com.example.raionapp.presentation.homePage.learningPage.LessonPages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont

@Composable
fun SubLessonLocked(
    modifier: Modifier = Modifier,
    subLessonName: String
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(
                color = Color(0xFF5598CC),
                shape = RoundedCornerShape(size = 10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = subLessonName,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 21.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
            )
        )

        Icon(
            painter = painterResource(R.drawable.lock_icon_lesson),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview
@Composable
fun SubLessonPreview() {
    SubLessonLocked(
        subLessonName = "Introduction to Website Design"
    )
}