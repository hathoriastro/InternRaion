package com.example.raionapp.presentation

import AppNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.ui.theme.RaionappTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    lateinit var context: android.content.Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()
//        Menambahkan AuthViewModel
        val authViewModel: AuthViewModel by viewModels()
        context = this
        setContent {
            RaionappTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        authViewModel = authViewModel,
                        context = context
                    )
                }


            }
        }
    }
}

