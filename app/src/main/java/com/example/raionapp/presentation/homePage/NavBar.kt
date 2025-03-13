package com.example.raionapp.presentation.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.authentication.AuthViewModel

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    Row(
        modifier = Modifier
            .background(
                color = Color(0xFFA7A7A7),
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            )
            .fillMaxWidth()
            .height(70.dp)
            .zIndex(3f),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column (
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(50.dp)
                .clickable { navController.navigate("home") },
        ){
            Icon(
                painter = painterResource(id = R.drawable.home_icon_unclicked),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                tint = Color.Black
            )

            Text(
                text = "Home",
                style = TextStyle(
                    fontFamily = montserratFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.Black
            )
        }

        Column (
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ){
            Icon(
                painter = painterResource(id = R.drawable.bank_soal_icon_unclicked),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 3.dp),
                tint = Color.Black
            )

            Text(
                text = "Bank",
                style = TextStyle(
                    fontFamily = montserratFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.Black
            )
        }

        Column (
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ){
            Icon(
                painter = painterResource(id = R.drawable.learn_icon_unclicked),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                tint = Color.Black
                )

            Text(
                text = "Learn",
                style = TextStyle(
                    fontFamily = montserratFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )
        }

        Column (
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { navController.navigate("profile") }
        ){
            Icon(
                painter = painterResource(id = R.drawable.profile_icon_unclicked),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                tint = Color.Black
            )

            Text(
                text = "Profile",
                style = TextStyle(
                    fontFamily = montserratFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.Black
            )
        }
    }
}