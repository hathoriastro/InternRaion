package com.example.raionapp.presentation.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun MentorRegistrationPage(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var fullName by remember { mutableStateOf("") }
    var fieldOfExpertise by remember { mutableStateOf("") }
    var teachingExperience by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) } // State to track dialog visibility

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Image(
            painter = painterResource(id = R.drawable.splashscreenpng),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 80.dp)
                .fillMaxWidth(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mentorship Registration",

                // Headline/H1 Head Bold
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
            )

            Text(
                text = "Complete the form below to apply as a mentor. Make sure your information is accurate.",

                // Body Text/Body 1 Medium
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(570.dp)
                .size(350.dp)
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(20.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Full Name",

                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = fullName,
                    onValueChange = {
                        fullName = it
                    },
                    placeholder = {
                        Text(
                            text = "Full Name",
                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF757575),
                            )
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF0F1F5),
                        focusedContainerColor = Color.White,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Field of Expertise",

                    // Body Text/Body Small Bold
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = fieldOfExpertise,
                    onValueChange = {
                        fieldOfExpertise = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text(
                            text = "e.g., Data Science, UI/UX, etc.",

                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF757575),
                            )
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF0F1F5),
                        focusedContainerColor = Color.White,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Teaching Experience",

                    // Body Text/Body Small Bold
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = teachingExperience,
                    onValueChange = {
                        teachingExperience = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text(
                            text = "e.g., Beginner, Intermediate, Advanced.",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                color = Color(0xFF757575),
                            )
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF0F1F5),
                        focusedContainerColor = Color.White,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Upload Certification",

                    // Body Text/Body Small Bold
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    Modifier
                        .shadow(elevation = 2.dp, spotColor = Color(0x3DE4E5E7), ambientColor = Color(0x3DE4E5E7))
                        .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(size = 10.dp))
                        .width(332.dp)
                        .height(46.dp)
                        .background(color = Color(0xFFF0F1F5), shape = RoundedCornerShape(size = 10.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box(
                        Modifier
                            .padding(10.dp)
                            .width(100.dp)
                            .height(24.dp)
                            .background(color = Color(0xFFD9D9D9)),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Choose File",
                            // Body Text/Body 12 Medium
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                    Text(
                        text = "No file chosen",

                        // Body Text/Body 12 Medium
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF757575),
                        )
                    )
                }

                Spacer(modifier = Modifier.height(70.dp))

                Box(
                    Modifier
                        .shadow(elevation = 2.dp, spotColor = Color(0x3DE4E5E7), ambientColor = Color(0x3DE4E5E7))
                        .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(size = 10.dp))
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(size = 10.dp))
                        .clickable {
                            showDialog = true // Show dialog on click
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Submit",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(700),
                            color = Color.White,
                        )
                    )
                }
            }
        }
    }

    // Fungsi Pop Up
    if (showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f)) // Semi-transparent background
                .clickable { showDialog = false }, // Close pop-up when clicking outside
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(0.8f)
                    .background(Color.White, shape = RoundedCornerShape(15.dp))
                    .padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Application Submitted",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Your mentor application has been submitted successfully. We will review your profile and notify you once approved.",
                        // Body Text/Body 12 Medium
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF111111),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(40.dp)
                            .background(Color(0xFF1A5294), shape = RoundedCornerShape(10.dp))
                            .clickable { navController.navigate("mycourse") }, // Close pop-up
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "My Course",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(40.dp)
                            .background(Color(0xFF1A5294), shape = RoundedCornerShape(10.dp))
                            .clickable { navController.navigate("profile") }, // Close pop-up
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Back",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MentorRegistrationPreview() {
    MentorRegistrationPage(
        navController = rememberNavController()
    )
}
