package com.example.raionapp.presentation.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.raionapp.R
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.profile.profileData

@Composable
fun TopBarAndProfile(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFF5598CC))
        ){

        }

//        Profile Picture
        val userProfileData = profileData(authViewModel = authViewModel)
        Image(
            painter = rememberAsyncImagePainter(
                model = userProfileData.value?.profilePicture,
                placeholder = painterResource(R.drawable.profile_picture),
                error = painterResource(R.drawable.profile_picture)
            ),
            contentDescription = "Foto Profil",
            modifier = Modifier
                .width(71.dp)
                .height(69.dp)
                .align(Alignment.TopEnd)
                .zIndex(2f)
                .offset(x = -10.dp, y = 50.dp)
                .clip(CircleShape)
        )

        Image(
            painter = painterResource(id = R.drawable.heading_eclipse),
            contentDescription = "Header Background",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = 110.dp, y = 0.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Header Background",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 10.dp, y = 30.dp)
                .size(80.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.heading_text_tutoria),
            contentDescription = "Header Background",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 85.dp, y = 60.dp)
        )    }
}

@Preview
@Composable
fun TopBarPreview() {
    TopBarAndProfile(authViewModel = null)
}