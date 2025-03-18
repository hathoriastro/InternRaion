package com.example.raionapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont

@Composable
fun AboutProfilePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(24.dp))
            ){
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow_icon),
                    contentDescription = "image description",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterStart)
                        .padding(start = 40.dp, top = 20.dp)
                        .clickable { navController.popBackStack() },
                    tint = Color.White
                )

                Text(
                    text = "About",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 20.dp)
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 30.dp))
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(330.dp, 150.dp)
                    .border(1.dp, color = Color(0xFF5598CC), RoundedCornerShape(10.dp))
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp)
                    ),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 20.dp)
                        .clickable {
                            navController.navigate("savedanswers")
                        },
                ) {
                    Text(
                        "Terms Of Use",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentWidth(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        lineHeight = 19.2.sp,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow_icon),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(17.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
                HorizontalDivider(color = Color(0xFF5598CC))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 20.dp)
                        .clickable {
                            navController.navigate("savedanswers")
                        },
                ) {
                    Text(
                        "Privacy Policy",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentWidth(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        lineHeight = 19.2.sp,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow_icon),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(17.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
                HorizontalDivider(color = Color(0xFF5598CC))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 20.dp)
                        .clickable {
                            navController.navigate("savedanswers")
                        },
                ) {
                    Text(
                        "About Your Account",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentWidth(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        lineHeight = 19.2.sp,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow_icon),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(17.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
                HorizontalDivider(color = Color(0xFF5598CC))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 20.dp)
                        .clickable {
                            navController.navigate("savedanswers")
                        },
                ) {
                    Text(
                        "Open Source Library",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .wrapContentWidth(),
                        color = Color.Black,
                        fontSize = 16.sp,
                        lineHeight = 19.2.sp,
                        fontWeight = FontWeight(500)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.right_arrow_icon),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .size(17.dp)
                            .align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AboutPagePreview() {
    AboutProfilePage(
        navController = rememberNavController()
    )
}