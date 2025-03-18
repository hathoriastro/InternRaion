package com.example.raionapp.presentation.profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.raionapp.firestore.model.ProfileDataClass
import com.example.raionapp.R
import com.example.raionapp.presentation.authentication.AuthState
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.NavBar

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
    context: Context
) {
    val authState = authViewModel?.authState?.collectAsState()
    LaunchedEffect(authState?.value) {
        when (authState?.value) {
            is AuthState.Unauthenticated -> navController.navigate("register")
            else -> Unit
        }
    }

    val userProfileData = profileData(authViewModel = authViewModel)

    Scaffold(
        bottomBar = {
            Surface(
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                NavBar(navController = navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Transparent)
        ) {
            Image(
                painter = painterResource(id = R.drawable.splashscreenpng),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(10.dp))
                        .align(Alignment.BottomCenter)
                ) {
                    Row(
                        modifier = Modifier
                            .size(240.dp, 80.dp)
                            .offset(y = -40.dp)
                            .shadow(8.dp, RoundedCornerShape(20.dp))
                            .align(Alignment.TopCenter)
                            .background(color = Color.White, RoundedCornerShape(20.dp)),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.CenterVertically),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.comment_count_icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                tint = Color.Black
                            )
                            Text(
                                "Question",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                            Text(
                                text = (userProfileData.value?.numberOfQuestion ?: 0).toString(),
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        Column(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.answer_count_icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                tint = Color.Black
                            )
                            Text(
                                "Answer",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                            )
                            Text(
                                text = (userProfileData.value?.numberOfAnswer ?: 0).toString(),
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
            ) {
                Spacer(modifier = Modifier.padding(vertical = 35.dp))

                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 30.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = userProfileData.value?.profilePicture,
                            placeholder = painterResource(R.drawable.profile_picture),
                            error = painterResource(R.drawable.profile_picture)
                        ),
                        contentDescription = "Foto Profil",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(128.dp)
                            .height(128.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    Text(
                        text = userProfileData.value?.fullname ?: "....",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 13.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .align(Alignment.BottomCenter)
                        .background(color = Color.Transparent)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.padding(vertical = 30.dp))
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(330.dp, 120.dp)
                            .shadow(8.dp, RoundedCornerShape(20.dp))
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable {
                                    navController.navigate("savedanswers")
                                },
                        ) {
                            Spacer(modifier = Modifier.padding(start = 20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.saved_answers_icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                tint = Color.Black
                            )
                            Text(
                                "Saved Answers",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 23.dp)
                                    .width(155.dp),
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
                                    .align(Alignment.CenterVertically)
                                    .offset(x = 83.dp)
                            )
                        }
                        HorizontalDivider(color = Color(0xFF5598CC))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable { },
                        ) {
                            Spacer(modifier = Modifier.padding(start = 20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.exported_questions_icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                tint = Color.Black
                            )
                            Text(
                                "Exported Questions",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 20.dp),
                                color = Color.Black,
                                fontSize = 16.sp,
                                lineHeight = 19.2.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow_icon),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(17.dp)
                                    .align(Alignment.CenterVertically)
                                    .offset(x = 89.dp)
                            )
                        }
                        HorizontalDivider(color = Color(0xFF5598CC))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable { },
                        ) {
                            Spacer(modifier = Modifier.padding(start = 20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.mentorship_icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                tint = Color.Black
                            )
                            Text(
                                "Mentorship",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 20.dp)
                                    .width(147.dp),
                                color = Color.Black,
                                fontSize = 16.sp,
                                lineHeight = 19.2.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow_icon),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(17.dp)
                                    .align(Alignment.CenterVertically)
                                    .offset(x = 83.dp)
                            )
                        }
                    }

                    Text(
                        "Settings",
                        modifier = Modifier
                            .width(155.dp)
                            .offset(x = 30.dp)
                            .padding(vertical = 15.dp),
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(330.dp, 170.dp)
                            .shadow(8.dp, RoundedCornerShape(20.dp))
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable { },
                        ) {
                            Spacer(modifier = Modifier.padding(start = 20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.faq_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .size(22.dp),
                                tint = Color.Black
                            )
                            Text(
                                "FAQ",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 15.dp)
                                    .width(155.dp),
                                color = Color.Black,
                                lineHeight = 19.2.sp,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow_icon),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(17.dp)
                                    .align(Alignment.CenterVertically)
                                    .offset(x = 75.dp)
                            )
                        }
                        HorizontalDivider(color = Color(0xFF5598CC))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable { },
                        ) {
                            Spacer(modifier = Modifier.padding(start = 20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.contact_us_icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                tint = Color.Black
                            )
                            Text(
                                "Contact Us",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 20.dp)
                                    .width(155.dp),
                                color = Color.Black,
                                fontSize = 16.sp,
                                lineHeight = 19.2.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow_icon),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(17.dp)
                                    .align(Alignment.CenterVertically)
                                    .offset(x = 77.dp)
                            )
                        }
                        HorizontalDivider(color = Color(0xFF5598CC))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable {
                                    navController.navigate("aboutprofilepage")
                                },
                        ) {
                            Spacer(modifier = Modifier.padding(start = 20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.about_icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                tint = Color.Black
                            )
                            Text(
                                "About",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 20.dp)
                                    .width(147.dp),
                                color = Color.Black,
                                fontSize = 16.sp,
                                lineHeight = 19.2.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow_icon),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(17.dp)
                                    .align(Alignment.CenterVertically)
                                    .offset(x = 83.dp)
                            )
                        }
                        HorizontalDivider(color = Color(0xFF5598CC))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable {
                                    authViewModel?.signOut(context)
                                },
                        ) {
                            Spacer(modifier = Modifier.padding(start = 20.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.log_out_icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                tint = Color.Black
                            )
                            Text(
                                "Log Out",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 20.dp)
                                    .width(147.dp),
                                color = Color.Black,
                                fontSize = 16.sp,
                                lineHeight = 19.2.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.right_arrow_icon),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(17.dp)
                                    .align(Alignment.CenterVertically)
                                    .offset(x = 83.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val mockProfileData = remember {
        mutableStateOf(ProfileDataClass(
            fullname = "John Doe",
            profilePicture = "",
            numberOfQuestion = 10,
            numberOfAnswer = 5
        ))
    }

    ProfilePage(
        navController = rememberNavController(),
        authViewModel = null,
        context = androidx.compose.ui.platform.LocalContext.current
    )
}