package com.example.raionapp.presentation.register

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.raionapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.presentation.authentication.AuthState
import com.example.raionapp.presentation.authentication.AuthViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(("")) }
    var userEmail by remember { mutableStateOf(("")) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var passwordError by remember { mutableStateOf("") }

    // backend
    val authState = authViewModel?.authState?.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState?.value) {
        when(authState?.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }


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

        Image(
            modifier = Modifier
                .padding(20.dp,70.dp)
                .size(190.dp),
            painter = painterResource(id = R.drawable.sign_up_now_text),
            contentDescription = "Welcome Back"
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
                Row {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .border(30.dp, color = Color.Transparent)
                            .width(150.dp),
                        shape = RoundedCornerShape(10.dp, 0.dp, 0.dp, 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ){
                        Text(
                            text = ("Sign Up"),
                            color = Color.Black
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate("register")
                                  },
                        modifier = Modifier
                            .border(30.dp, color = Color.Transparent)
                            .width(150.dp),
                        shape = RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF0F1F5)
                        )
                    ){
                        Text(
                            text = ("Log In"),
                            color = Color.Black
                        )
                    }

                }

                Spacer(modifier = Modifier.height(40.dp))

                Image(
                    painter = painterResource(id = R.drawable.username_text),
                    contentDescription = " "
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    placeholder = {Text("Username")},
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
                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.email_small_text),
                    contentDescription = " "
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = userEmail,
                    onValueChange = {
                        userEmail = it
                    },
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {Text("Email Address")},
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

                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.password_text),
                    contentDescription = " "
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = password,
                    onValueChange = {
                        password = it
                        isPasswordValid = validatePassword(it)
                        if (!isPasswordValid) {
                            passwordError = "Password must be at least 6 characters long"
                        } else {
                            passwordError = ""
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text("Password") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = if (isPasswordValid) Color(0xFFF0F1F5) else Color.Red.copy(alpha = 0.1f),
                        focusedContainerColor = if (isPasswordValid) Color.White else Color.Red.copy(alpha = 0.1f),
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    isError = !isPasswordValid
                )

                if (!isPasswordValid) {
                    Text(
                        text = passwordError,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    modifier = Modifier
                        .clickable {
                            if (validatePassword(password)) {
                                Log.d("SignUpScreen", "Image clicked")
                                authViewModel?.signIn(userEmail, password) // backend
                            } else {
                                isPasswordValid = false
                                passwordError = "Password must be at least 6 characters long"
                            }
                        }
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.sign_up_button),
                    contentDescription = "Log In Button"
                )

                Image(
                    painter = painterResource(id = R.drawable.or_line),
                    contentDescription = "Or Line",
                    modifier = Modifier.height(40.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.continue_with_google),
                    contentDescription = " ",
                    modifier = Modifier
                        .clickable {
                            authViewModel?.signInGoogle(context)
                        }
                )
            }
        }

        /*Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.half_frame_background),
            contentDescription = "Half Frame Background"
        )*/


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
fun SignUpScreenPreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    SignUpScreen(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel
    )
}