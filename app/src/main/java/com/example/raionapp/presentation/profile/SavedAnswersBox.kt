package com.example.raionapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.homePage.SavedAnswers
import java.time.format.TextStyle

@Composable
fun SavedAnswerBox(
    id : String,
    modifier: Modifier = Modifier
) {
    Column(

    ){
        Box(
            Modifier
                .border(
                    width = 1.dp,
                    color = Color(0xFFFDCB1A),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .width(191.dp)
                .height(188.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 16.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.cloud_saved_answer),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Text(
            text = id,

            // Body Text/Body 1 Bold
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.4.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Preview
@Composable
private fun SavedAnswerBoxPreview() {
    SavedAnswerBox(
        "All Answer"
    )
}