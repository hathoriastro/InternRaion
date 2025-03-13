package com.example.raionapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.backend.loginAndRegister.AuthViewModel
import com.example.raionapp.presentation.homePage.NavBar

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
){
    Scaffold (
        bottomBar = {
            Surface(
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                NavBar(navController = navController, authViewModel = authViewModel)
            }
        }
    ){
            paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
        ){
            Box(modifier = Modifier.fillMaxSize()) {
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(380.dp)
                        .zIndex(1f),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFD9D9D9))
                    )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
            ){

                Spacer(modifier = Modifier.padding(vertical = 35.dp))
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    "Username",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.padding(vertical = 13.dp))

                Row(
                    modifier = Modifier
                        .size(240.dp, 80.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(color = Color(0xFF757575), RoundedCornerShape(20.dp)),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.comment_count_icon),
                            contentDescription =  null,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Text(
                            "Question",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )

                        Text(
                            "0",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.answer_count_icon),
                            contentDescription =  null,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Text(
                            "Answer",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )

                        Text(
                            "0",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                }

                Spacer(modifier = Modifier.padding(vertical = 20.dp))

                Column(
                    modifier = Modifier
                        .size(330.dp,120.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(color = Color(0xFF7D7F83), shape = RoundedCornerShape(20.dp))
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ){
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.saved_answers_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            tint = Color.White
                        )

                        Text(
                            "Saved Answers",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 20.dp)
                                .width(155.dp),
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(17.dp)
                                .align(Alignment.CenterVertically)
                                .offset(x = 83.dp)
                        )
                    }
                    Divider(color = Color.White)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ){
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.exported_questions_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            tint = Color.White
                        )

                        Text(
                            "Exported Questions",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 20.dp),
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(17.dp)
                                .align(Alignment.CenterVertically)
                                .offset(x = 83.dp)
                        )
                    }

                    Divider(color = Color.White)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ){
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.mentorship_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            tint = Color.White
                        )

                        Text(
                            "Mentorship",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 20.dp)
                                .width(147.dp),
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow_icon),
                            contentDescription = null,
                            tint = Color.White,
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
                        .size(330.dp,120.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(color = Color(0xFF7D7F83), shape = RoundedCornerShape(20.dp))
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ){
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.faq_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            tint = Color.White
                        )

                        Text(
                            "FAQ",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 15.dp)
                                .width(155.dp),
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(17.dp)
                                .align(Alignment.CenterVertically)
                                .offset(x = 75.dp)
                        )
                    }
                    Divider(color = Color.White)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ){
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.contact_us_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            tint = Color.White
                        )

                        Text(
                            "Contact Us",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 20.dp)
                                .width(155.dp),
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow_icon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(17.dp)
                                .align(Alignment.CenterVertically)
                                .offset(x = 77.dp)
                        )
                    }

                    Divider(color = Color.White)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ){
                        Spacer(modifier = Modifier.padding(start = 20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.about_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            tint = Color.White
                        )

                        Text(
                            "About",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 20.dp)
                                .width(147.dp),
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.right_arrow_icon),
                            contentDescription = null,
                            tint = Color.White,
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
     /*Column(
         modifier = Modifier
             .fillMaxSize()
             .background(color = Color.White)
     ){
         Image(
             painter = painterResource(id = R.drawable.profile_picture),
             contentDescription = null,
             modifier = Modifier
                 .align(Alignment.CenterHorizontally)
                 .offset(y = 70.dp)
         )

     }*/
}

@Preview
@Composable
fun ProfilePagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null
) {
    ProfilePage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel
    )
}