package com.example.raionapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont

@Composable
fun BecomeAMentorPage(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .width(430.dp)
                .height(200.dp)
                .background(
                    color = Color(0xFF5598CC),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 24.dp,
                        bottomEnd = 24.dp
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Become a Mentor!",

                    // Headline/H1 Head Bold
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Share your knowledge, guide your peers, and grow as a mentor!",

                    // Body Text/Body 1 Medium
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }

        }

        Box(
            Modifier
                .align(Alignment.Center)
                .border(
                    width = 1.dp,
                    color = Color(0xFF1A5294),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth(0.85f)
                .height(340.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp)),
        ){
            Text(
                text = "Benefits of Being a Mentor",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF111111),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 20.dp)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color(0xFFFDCB1A), CircleShape),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mentorship_checklist_icon),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Enhance Your Skills",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF111111),
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color(0xFFFDCB1A), CircleShape),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mentorship_checklist_icon),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Expand your Network",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF111111),
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color(0xFFFDCB1A), CircleShape),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mentorship_checklist_icon),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Boost your Career Prospect",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF111111),
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color(0xFFFDCB1A), CircleShape),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mentorship_checklist_icon),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Build Confidence",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF111111),
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color(0xFFFDCB1A), CircleShape),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mentorship_checklist_icon),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Make Impact",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF111111),
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color(0xFFFDCB1A), CircleShape),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mentorship_checklist_icon),
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Reinforce Your Knowledge",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF111111),
                        )
                    )
                }
            }

        }

        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .offset(y = -70.dp)
                .shadow(elevation = 2.dp, spotColor = Color(0x3DE4E5E7), ambientColor = Color(0x3DE4E5E7))
                .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(size = 10.dp))
                .width(327.dp)
                .height(46.dp)
                .background(color = Color(0xFFFDCB1A), shape = RoundedCornerShape(size = 10.dp))
                .clickable {
                    navController.navigate("mentorregistration")
                },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Start Registration",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 19.6.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
            )
        }
    }
}

@Preview
@Composable
private fun BecomeAMentorPagePreview() {
    BecomeAMentorPage(
        navController = rememberNavController()
    )
}