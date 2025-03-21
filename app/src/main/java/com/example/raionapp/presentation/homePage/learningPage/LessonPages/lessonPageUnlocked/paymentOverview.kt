package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.TopBarAndProfile
import com.example.raionapp.presentation.homePage.threads.ThreadContent

@Composable
fun PaymentOverviewPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel?
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        TopBarAndProfile(
            modifier = modifier,
            navController = navController,
            authViewModel = authViewModel,
        )
        Column(
            Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .fillMaxSize()
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            ) {
                Row(
                    modifier = Modifier
                        .offset(20.dp, 20.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back_button),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.92f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth(0.84f)
                            .height(120.dp)
                            .border(1.dp, Color(0xFF1A5294), RoundedCornerShape(10.dp))
                            .background(Color.White, shape = RoundedCornerShape(size = 10.dp)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Column(
                        ){
                            Box(
                                Modifier
                                    .padding(0.dp)
                                    .width(53.dp)
                                    .height(53.dp)
                                    .background(Color(0xFFFDCB1A), CircleShape)
                            ){
                                Text(
                                    text = "1",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 27.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFFFFFFF),
                                    ),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Text(
                                text = "Overview",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                )
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Box(
                                Modifier
                                    .padding(0.dp)
                                    .width(53.dp)
                                    .height(53.dp)
                                    .border(1.dp, Color(0xFFFDCB1A), CircleShape)
                                    .background(Color.White, CircleShape),
                            ){
                                Text(
                                    text = "2",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 27.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFFDCB1A),
                                    ),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Text(
                                text = "Payment Method",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                )
                            )
                        }

                        Column(
                        ){
                            Box(
                                Modifier
                                    .padding(0.dp)
                                    .width(53.dp)
                                    .height(53.dp)
                                    .border(1.dp, Color(0xFFFDCB1A), CircleShape)
                                    .background(Color.White, CircleShape)
                            ){
                                Text(
                                    text = "3",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 27.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFFDCB1A),
                                    ),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Text(
                                text = "Confirmation",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                )
                            )
                        }

                    }
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize(0.9f),
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ){
                        Column {
                            Text(
                                text = "Overview",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(132.dp)
                                    .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(size = 10.dp))
                            ){

                            }
                        }
                        Column(
                            Modifier
                                .border(width = 1.dp, color = Color(0xFF1A5294), shape = RoundedCornerShape(size = 10.dp))
                                .width(351.dp)
                                .height(132.dp)
                                .padding(horizontal = 20.dp),
                            verticalArrangement = Arrangement.SpaceAround
                        ){
                            Text(
                                text = "Purchase Summary",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF000000),
                                )
                            )
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = "Price incl. tax",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 21.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF000000),
                                    )
                                )

                                Text(
                                    text = "100.00$",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 21.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF000000),
                                    )
                                )

                            }

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = "Coupon",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 21.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF000000),
                                    )
                                )

                                Text(
                                    text = "15.00$",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 21.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF000000),
                                    )
                                )
                            }

                            HorizontalDivider()
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = "Total",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 21.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF000000),
                                    )
                                )

                                Text(
                                    text = "75.00$",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 21.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF000000),
                                    )
                                )
                            }
                        }
                        Box(Modifier
                            .align(Alignment.CenterHorizontally)
                            .shadow(elevation = 2.dp, spotColor = Color(0x3DE4E5E7), ambientColor = Color(0x3DE4E5E7))
                            .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(size = 10.dp))
                            .width(327.dp)
                            .height(46.dp)
                            .background(color = Color(0xFFFDCB1A), shape = RoundedCornerShape(size = 10.dp))
                        ){
                            Text(
                                text = "Continue",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFFFFFFF),
                                ),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PaymentOverviewPagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    PaymentOverviewPage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
    )

}

