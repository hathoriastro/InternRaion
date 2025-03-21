package com.example.raionapp.presentation.homePage.bankPage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionapp.common.montserratFont

@Composable
fun BankListContent(
    contentTitle : String,
    modifier: Modifier = Modifier
) {
    Box(
        Modifier
            .shadow(8.dp, RoundedCornerShape(15.dp))
            .width(398.dp)
            .height(154.dp)
            .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(size = 17.dp))
    ){
        Box(
            modifier = Modifier
                .width(398.dp)
                .fillMaxHeight(0.55f)
                .background(color = Color.White, shape = RoundedCornerShape(topStart =  17.dp, topEnd = 17.dp))
        ){

        }
        Text(
            text = contentTitle,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(0.55f)
                .offset(x = 10.dp, y = -20.dp)
        )

        Box(
            Modifier
                .align(Alignment.BottomEnd)
                .offset(x = -10.dp, y = -10.dp)
                .width(97.dp)
                .height(24.dp)
                .background(color = Color(0xFFFDBA21), shape = RoundedCornerShape(size = 16.dp))
        ){
            Text(
                text = "View More",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.align(Alignment.Center)
            )

        }

    }
}

@Preview
@Composable
private fun BankListContentPreview() {
    BankListContent(
        contentTitle = "Halo Bang"
    )
}