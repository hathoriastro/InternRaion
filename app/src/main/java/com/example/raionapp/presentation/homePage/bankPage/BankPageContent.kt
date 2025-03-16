package com.example.raionapp.presentation.homePage.bankPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.raionapp.common.montserratFont

@Composable
fun BankContent(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .width(202.dp)
            .height(119.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 15.dp))
            .shadow(0.05.dp, RoundedCornerShape(1.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.bank_page_dummy_png),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
        )

        Text(
            text = "Cryptocurrency",
            style = TextStyle(
                fontSize = 9.sp,
                lineHeight = 13.5.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(400),
                color = Color.Black,
                letterSpacing = 0.05.em,
            ),
            modifier = Modifier
                .padding(start = 10.dp, top = 9.dp)
        )
        Spacer(Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable._5_selesai_dummy),
            contentDescription = "Progress,",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "35% Selesai",
            style = TextStyle(
                fontSize = 9.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(600),
                color = Color(0xFF757575),
            ),
            modifier = Modifier.padding(start = 10.dp)
        )

    }
}

@Preview
@Composable
private fun LearningContentPreview() {
    BankContent()
}