package com.example.raionapp.presentation.homePage.subjectSelect

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.TopBarAndProfile

@Composable
fun SubjectSelectPage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
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
            Spacer(modifier = Modifier.height(150.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .zIndex(2f)
                    .fillMaxSize()
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Pilih Subject",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                        letterSpacing = 0.6.sp,
                    ),
                    modifier = Modifier.offset(20.dp, 20.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.x_icon_close),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(-20.dp, 20.dp)
                        .size(24.dp)
                        .clickable { navController.popBackStack() },
                    tint = Color.Black
                )
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                        )
                        .align(Alignment.BottomCenter)
                        .zIndex(2f)
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.92f)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SubjectSelectButton(
                        modifier = Modifier,
                        navController = navController,
                        authViewModel = authViewModel,
                    )
                    SubjectSelectButton(
                        modifier = Modifier,
                        navController = navController,
                        authViewModel = authViewModel,
                    )
                    SubjectSelectButton(
                        modifier = Modifier,
                        navController = navController,
                        authViewModel = authViewModel,
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun SubjectSelectPagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    SubjectSelectPage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
    )
}
