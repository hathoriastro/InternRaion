package com.example.raionapp.presentation.homePage

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.presentation.authentication.AuthState
import com.example.raionapp.presentation.authentication.AuthViewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
) {
    // Backend AuthViewModel
    val authState = authViewModel?.authState?.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState?.value) {
        when(authState?.value) {
            is AuthState.Unauthenticated -> navController.navigate("register")
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Page", fontSize = 32.sp)

        Button(
            modifier = modifier,
            onClick = {
                    authViewModel?.signOut(context)
        }) {
            Text(text = "Sign Out")
        }
    }
}

@Preview
@Composable
fun HomePagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    HomePage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
    )
}