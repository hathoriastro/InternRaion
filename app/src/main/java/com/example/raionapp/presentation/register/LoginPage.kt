package com.example.raionapp.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.raionapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.round

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
var Username by remember { mutableStateOf("") }
var Pass by remember { mutableStateOf(("")) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {

        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(0.dp,20.dp),
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo"
        )

        Image(
            modifier = Modifier
                .padding(20.dp,50.dp)
                .size(190.dp),
            painter = painterResource(id = R.drawable.welcome_back),
            contentDescription = "Welcome Back"
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(600.dp)
                .size(350.dp)
                .fillMaxSize()
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
        ){

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(20.dp)
            ) {

                Spacer(modifier = Modifier.height(5.dp))
                Image(
                    painter = painterResource(id = R.drawable.login_and_signup),
                    contentDescription = "Sign Up Button"
                )

                Spacer(modifier = Modifier.height(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.username_text),
                    contentDescription = " "
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = Username,
                    onValueChange = {
                        Username = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    label = {
                        Text(text = "Username")
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF0F1F5),
                        focusedContainerColor = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.password_text),
                    contentDescription = " "
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = Pass,
                    onValueChange = {
                        Pass = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    label = {
                        Text(text = "Password")
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF0F1F5),
                        focusedContainerColor = Color.White,
                        unfocusedPlaceholderColor = Color.Transparent
                    )
                )
                /*Image(
                    modifier = Modifier.clickable {  },
                    painter = painterResource(id = R.drawable.username_input),
                    contentDescription = "Username"
                )*/


                Spacer(modifier = Modifier.height(20.dp))
                Row( ){
                    Icon(
                        modifier = Modifier.clickable {  },
                        painter = painterResource(id = R.drawable.check_box_outline),
                        contentDescription = "Check Box"
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Remember Me", modifier = Modifier.align(Alignment.CenterVertically))

                    Spacer(modifier = Modifier.width(75.dp))
                    Text(
                        text = "Forgot Password?",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {  },
                        color = Color(0xFF1A5294)

                    )

                }

                Spacer(modifier = Modifier.height(20.dp))
                /* Image(
                    modifier = Modifier
                        .clickable { }
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.log_in_button),
                    contentDescription = "Log In Button"
                ) */

                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.or_line),
                    contentDescription = "Or Line",
                    modifier = Modifier.height(60.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.continue_with_google),
                    contentDescription = " ",
                    modifier = Modifier
                        .clickable {  }
                )
            }

        }

        /* Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.half_frame_background),
            contentDescription = "Half Frame Background"
        ) */

        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )

        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_button),
                contentDescription = "Back"
            )
        }

        /*Column(
            modifier = Modifier
                .align(Alignment.Center)
        ){
            TextField(
                value = Username,
                modifier = Modifier
                    .padding(16.dp)
                    .width(290.dp),
                onValueChange = {
                    Username = it
                },
                label = { Text("Name") },
            )

            TextField(
                value = Pass,
                modifier = Modifier
                    .padding(16.dp)
                    .width(290.dp),
                onValueChange = {
                    Pass = it
                },
                label = { Text ("Password")}
            )
        } */


    }

}


@Preview
@Composable
fun RegisterScreenPreview(modifier: Modifier = Modifier) {
    RegisterScreen()
    
}