package com.example.raionapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.homePage.model.profileData
import com.example.raionapp.presentation.register.AuthViewModel
import com.example.raionapp.presentation.profile.AnswerBox

@Composable
fun SavedAnswers(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    var thread by remember { mutableStateOf("") }

//  Kirim Thread ke Firestore
    val authorProfileData = profileData(authViewModel)
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
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
                    text = "Saved Answers",
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
            Spacer(modifier = Modifier.padding(vertical = 20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                AnswerBox("All Questions")
                AnswerBox("Kedokteran")
            }
        }
    }
}

@Preview
@Composable
private fun SavedAnswersPreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null
) {
    SavedAnswers(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel
    )
}