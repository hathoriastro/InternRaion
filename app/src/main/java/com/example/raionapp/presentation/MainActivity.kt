package com.example.raionapp.presentation

import AppNavHost
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.ui.theme.RaionappTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()

        val authViewModel: AuthViewModel by viewModels()

        setContent {
            RaionappTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { /* Remove this if it's showing an unwanted toolbar */ }
                ) { innerPadding ->
                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        authViewModel = authViewModel,
                        context = this
                    )
                }
            }
        }
    }
}
