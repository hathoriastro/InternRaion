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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.math.round

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var UserEmail by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {

        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(0.dp,40.dp)
                .size(90.dp),
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Logo"
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(570.dp)
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
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.forgot_password_header),
                    contentDescription = "Sign Up Button"
                )

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.enter_email_txt),
                    contentDescription = " "
                )

                Spacer(modifier = Modifier.height(200.dp))

                Image(
                    painter = painterResource(id = R.drawable.email_small_text),
                    contentDescription = " "
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = UserEmail,
                    onValueChange = {
                        UserEmail = it
                    },
                    placeholder = {Text("Email Address")},
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF0F1F5),
                        focusedContainerColor = Color.White,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))


                /*Image(
                    modifier = Modifier.clickable {  },
                    painter = painterResource(id = R.drawable.username_input),
                    contentDescription = "Username"
                )*/


                Spacer(modifier = Modifier.height(20.dp))


                // Ini perubahannya, coba git

                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    modifier = Modifier
                        .clickable { }
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.reset_password_button),
                    contentDescription = "Log In Button"
                )

            }

        }

        /*Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.half_frame_background),
            contentDescription = "Half Frame Background"
        )*/

        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )

        ) {
            Icon(
                modifier = Modifier.padding(0.dp, 40.dp),
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
fun ForgotPasswordScreenReview(modifier: Modifier = Modifier) {
    ForgotPasswordScreen(navController = rememberNavController())

}