package com.example.raionapp.mentorship

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.firestore.model.LessonDataClass
import com.example.raionapp.presentation.homePage.model.LearningPageViewModel
import com.example.raionapp.presentation.homePage.model.profileData
import com.example.raionapp.presentation.register.AuthViewModel

@Preview(showBackground = true)
@Composable
fun CreateNewSubLessonPagePreview() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    CreateNewSubLessonPage(
        navController = navController,
        authViewModel = authViewModel
    )
}

@Composable
fun CreateNewSubLessonPage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val learningPageViewModel: LearningPageViewModel = viewModel()
    val authorProfileData = profileData(authViewModel)

    var courseName by remember { mutableStateOf("") }
    var about by remember { mutableStateOf("") }
    var numberOfMaterials by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var courseDuration by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }
    var numberOfSubsection by remember { mutableStateOf("") }
    var tittleOfSubsection by remember { mutableStateOf("") }
    var subsectionDescription by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ){
        Column {
            Box(
                modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(24.dp))
            ){
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow_icon),
                    contentDescription = "image description",
                    modifier = modifier
                        .size(50.dp)
                        .align(Alignment.CenterStart)
                        .padding(start = 40.dp, top = 20.dp)
                        .clickable { navController.popBackStack() },
                    tint = Color.White
                )

                Text(
                    text = "Create New Class",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = modifier
                        .align(Alignment.Center)
                        .padding(top = 20.dp)
                )
            }


            Column(
                modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Course Name",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(modifier = modifier.height(10.dp))

                    TextField(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = courseName,
                        onValueChange = {
                            courseName = it
                        },
                        placeholder = {
                            Text(
                                text = "Example: Learn UI/UX from Scratch",
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
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "About",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(modifier = modifier.height(10.dp))

                    TextField(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = about,
                        onValueChange = {
                            about = it
                        },
                        placeholder = {
                            Text(
                                text = "Briefly describe this course",
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
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Number of Materials",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(modifier = modifier.height(10.dp))

                    TextField(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = numberOfMaterials,
                        onValueChange = {
                            numberOfMaterials = it
                        },
                        placeholder = {
                            Text(
                                text = "Enter the total number",
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
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Price",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(modifier = modifier.height(10.dp))

                    TextField(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = price,
                        onValueChange = {
                            price = it
                        },
                        placeholder = {
                            Text(
                                text = "Enter course price",
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
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Subject",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(modifier = modifier.height(10.dp))

                    TextField(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = subject,
                        onValueChange = {
                            subject = it
                        },
                        placeholder = {
                            Text(
                                text = "Enter course subject",
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
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Course Duration",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(modifier = modifier.height(10.dp))

                    TextField(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = courseDuration,
                        onValueChange = {
                            courseDuration = it
                        },
                        placeholder = {
                            Text(
                                text = "How long is this course",
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
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

                Column(
                    modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Language",

                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    )

                    Spacer(modifier = modifier.height(10.dp))

                    TextField(
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .width(500.dp),
                        value = language,
                        onValueChange = {
                            language = it
                        },
                        placeholder = {
                            Text(
                                text = "The primary language of the course",
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
                            focusedContainerColor = Color(0xFFF0F1F5),
                            focusedPlaceholderColor = Color.LightGray,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )
                }

//                Column(
//                    modifier.fillMaxWidth(),
//                ) {
//                    Spacer(modifier = modifier.padding(vertical = 10.dp))
//                    Text(
//                        text = "Number of Subsection",
//
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = montserratFont,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF000000),
//                        )
//                    )
//
//                    Spacer(modifier = modifier.height(10.dp))
//
//                    TextField(
//                        modifier = modifier
//                            .align(Alignment.CenterHorizontally)
//                            .width(500.dp),
//                        value = numberOfSubsection,
//                        onValueChange = {
//                            numberOfSubsection = it
//                        },
//                        placeholder = {
//                            Text(
//                                text = "Select the total number",
//                                // Body Text/Body Small Medium
//                                style = TextStyle(
//                                    fontSize = 14.sp,
//                                    lineHeight = 19.6.sp,
//                                    fontFamily = montserratFont,
//                                    fontWeight = FontWeight(500),
//                                    color = Color(0xFF757575),
//                                )
//                            )
//                        },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = TextFieldDefaults.colors(
//                            unfocusedContainerColor = Color(0xFFF0F1F5),
//                            focusedContainerColor = Color(0xFFF0F1F5),
//                            focusedPlaceholderColor = Color.LightGray,
//                            unfocusedIndicatorColor = Color.Transparent,
//                            focusedIndicatorColor = Color.Transparent,
//                            focusedTextColor = Color.Black,
//                            unfocusedTextColor = Color.Black
//                        )
//                    )
//                }
//
//                Column(
//                    modifier.fillMaxWidth(),
//                ) {
//                    Spacer(modifier = modifier.padding(vertical = 10.dp))
//                    Text(
//                        text = "Tittle of subsection",
//
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = montserratFont,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF000000),
//                        )
//                    )
//
//                    Spacer(modifier = modifier.height(10.dp))
//
//                    TextField(
//                        modifier = modifier
//                            .align(Alignment.CenterHorizontally)
//                            .width(500.dp),
//                        value = tittleOfSubsection,
//                        onValueChange = {
//                            tittleOfSubsection = it
//                        },
//                        placeholder = {
//                            Text(
//                                text = "Enter the title of subsection",
//                                // Body Text/Body Small Medium
//                                style = TextStyle(
//                                    fontSize = 14.sp,
//                                    lineHeight = 19.6.sp,
//                                    fontFamily = montserratFont,
//                                    fontWeight = FontWeight(500),
//                                    color = Color(0xFF757575),
//                                )
//                            )
//                        },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = TextFieldDefaults.colors(
//                            unfocusedContainerColor = Color(0xFFF0F1F5),
//                            focusedContainerColor = Color(0xFFF0F1F5),
//                            focusedPlaceholderColor = Color.LightGray,
//                            unfocusedIndicatorColor = Color.Transparent,
//                            focusedIndicatorColor = Color.Transparent,
//                            focusedTextColor = Color.Black,
//                            unfocusedTextColor = Color.Black
//                        )
//                    )
//                }
//
//                Column(
//                    modifier.fillMaxWidth(),
//                ) {
//                    Spacer(modifier = modifier.padding(vertical = 10.dp))
//                    Text(
//                        text = "Subsection Description",
//
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = montserratFont,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF000000),
//                        )
//                    )
//
//                    Spacer(modifier = modifier.height(10.dp))
//
//                    TextField(
//                        modifier = modifier
//                            .align(Alignment.CenterHorizontally)
//                            .width(500.dp),
//                        value = subsectionDescription,
//                        onValueChange = {
//                            subsectionDescription = it
//                        },
//                        placeholder = {
//                            Text(
//                                text = "Enter the subsection division",
//                                // Body Text/Body Small Medium
//                                style = TextStyle(
//                                    fontSize = 14.sp,
//                                    lineHeight = 19.6.sp,
//                                    fontFamily = montserratFont,
//                                    fontWeight = FontWeight(500),
//                                    color = Color(0xFF757575),
//                                )
//                            )
//                        },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = TextFieldDefaults.colors(
//                            unfocusedContainerColor = Color(0xFFF0F1F5),
//                            focusedContainerColor = Color(0xFFF0F1F5),
//                            focusedPlaceholderColor = Color.LightGray,
//                            unfocusedIndicatorColor = Color.Transparent,
//                            focusedIndicatorColor = Color.Transparent,
//                            focusedTextColor = Color.Black,
//                            unfocusedTextColor = Color.Black
//                        )
//                    )
//                }
//
//                Spacer(modifier = modifier.height(30.dp))
//                Column(
//                    Modifier
//                        .fillMaxWidth()
//                ) {
//                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
//                    Text(
//                        text = "Upload File",
//
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = montserratFont,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF000000),
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Box(
//                        Modifier
//                            .shadow(
//                                elevation = 2.dp,
//                                spotColor = Color(0x3DE4E5E7),
//                                ambientColor = Color(0x3DE4E5E7)
//                            )
//                            .border(
//                                width = 1.dp,
//                                color = Color.Transparent,
//                                shape = RoundedCornerShape(size = 10.dp)
//                            )
//                            .width(100.dp)
//                            .height(46.dp)
//                            .background(
//                                color = Color(0xFFF0F1F5),
//                                shape = RoundedCornerShape(size = 10.dp)
//                            )
//                            .clickable {
//
//                            }
//
//                    ) {
//                        Icon(
//                            painter = painterResource(R.drawable.upload_file_icon),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .align(Alignment.CenterStart)
//                                .offset(x = 5.dp)
//                        )
//                        Text(
//                            text = "Upload",
//
//                            // Body Text/Body Small Medium
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                lineHeight = 19.6.sp,
//                                fontFamily = montserratFont,
//                                fontWeight = FontWeight(500),
//                                color = Color(0xFF757575),
//                            ),
//                            modifier = Modifier
//                                .align(Alignment.Center)
//                                .offset(10.dp)
//                        )
//
//                    }
//                }
//
//                Column(
//                    Modifier
//                        .fillMaxWidth()
//                ) {
//                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
//                    Text(
//                        text = "Upload Video",
//
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = montserratFont,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF000000),
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Box(
//                        Modifier
//                            .shadow(
//                                elevation = 2.dp,
//                                spotColor = Color(0x3DE4E5E7),
//                                ambientColor = Color(0x3DE4E5E7)
//                            )
//                            .border(
//                                width = 1.dp,
//                                color = Color.Transparent,
//                                shape = RoundedCornerShape(size = 10.dp)
//                            )
//                            .width(100.dp)
//                            .height(46.dp)
//                            .background(
//                                color = Color(0xFFF0F1F5),
//                                shape = RoundedCornerShape(size = 10.dp)
//                            )
//                            .clickable {
//
//                            }
//
//                    ) {
//                        Icon(
//                            painter = painterResource(R.drawable.upload_file_icon),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .align(Alignment.CenterStart)
//                                .offset(x = 5.dp)
//                        )
//                        Text(
//                            text = "Upload",
//
//                            // Body Text/Body Small Medium
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                lineHeight = 19.6.sp,
//                                fontFamily = montserratFont,
//                                fontWeight = FontWeight(500),
//                                color = Color(0xFF757575),
//                            ),
//                            modifier = Modifier
//                                .align(Alignment.Center)
//                                .offset(10.dp)
//                        )
//
//                    }
//                }
//
//                Column(
//                    Modifier
//                        .fillMaxWidth()
//                ) {
//                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
//                    Text(
//                        text = "Upload Video Practice",
//
//                        style = TextStyle(
//                            fontSize = 14.sp,
//                            fontFamily = montserratFont,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF000000),
//                        )
//                    )
//
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Box(
//                        Modifier
//                            .shadow(
//                                elevation = 2.dp,
//                                spotColor = Color(0x3DE4E5E7),
//                                ambientColor = Color(0x3DE4E5E7)
//                            )
//                            .border(
//                                width = 1.dp,
//                                color = Color.Transparent,
//                                shape = RoundedCornerShape(size = 10.dp)
//                            )
//                            .width(100.dp)
//                            .height(46.dp)
//                            .background(
//                                color = Color(0xFFF0F1F5),
//                                shape = RoundedCornerShape(size = 10.dp)
//                            )
//                            .clickable {
//
//                            }
//
//                    ) {
//                        Icon(
//                            painter = painterResource(R.drawable.upload_file_icon),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .align(Alignment.CenterStart)
//                                .offset(x = 5.dp)
//                        )
//                        Text(
//                            text = "Upload",
//
//                            // Body Text/Body Small Medium
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                lineHeight = 19.6.sp,
//                                fontFamily = montserratFont,
//                                fontWeight = FontWeight(500),
//                                color = Color(0xFF757575),
//                            ),
//                            modifier = Modifier
//                                .align(Alignment.Center)
//                                .offset(10.dp)
//                        )
//
//                    }
//                }

                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    Modifier
                        .shadow(
                            elevation = 2.dp,
                            spotColor = Color(0x3DE4E5E7),
                            ambientColor = Color(0x3DE4E5E7)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .width(332.dp)
                        .height(46.dp)
                        .background(
                            color = Color(0xFF1A5294),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .clickable {
                            learningPageViewModel.sendLesson(
                                authorProfile = authorProfileData.value,
                                lessonName = courseName,
                                about = about,
                                price = price,
                                subject = subject,
                                duration = courseDuration,
                                language = language,
                            )
                        },
                ){
                    Text(
                        text = "Create Class",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = modifier.align(Alignment.Center)
                    )
                }
                Spacer(modifier = modifier.height(30.dp))
            }
        }
    }
}