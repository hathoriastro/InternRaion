package com.example.raionapp.presentation.register

import android.content.Context
import android.credentials.CredentialManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.raionapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.authentication.AuthState
import com.example.raionapp.presentation.authentication.AuthViewModel
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.raionapp.common.montserratFont
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
    context: Context
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(("")) }
    var userEmail by remember { mutableStateOf(("")) }
    var fullname by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(true) }
    var passwordError by remember { mutableStateOf("") }

    // backend
    val authState = authViewModel?.authState?.collectAsState()
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
            painter = painterResource(id = R.drawable.splashscreenpng),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(0.dp,40.dp)
                .size(90.dp),
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Logo"
        )

        Column(
            modifier = Modifier.offset(x = 30.dp, y = 150.dp)
        ){
            Text(
                text = "Sign Up Now!",

                // Headline/H1 Head Bold
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier
                    .width(219.dp)
                    .height(39.dp)
            )

            Text(
                text = "Welcome to the future of learning - Sign Up ",

                // Body Text/Body 1 Medium
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
            Text(
                text = "and be part of it!",

                // Body Text/Body 1 Medium
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 22.4.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
        }

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
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFFF5F6F9), shape = RoundedCornerShape(size = 7.dp))
                        .width(332.dp)
                        .height(46.dp)
                        .background(color = Color(0xFFF5F6F9), shape = RoundedCornerShape(size = 7.dp))
                        .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width(161.dp)
                            .fillMaxHeight()
                            .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                            .background(Color.White)
                            .clickable {  }
                    ) {
                        Text(
                            text = "Sign Up",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF232447),
                            ),
                            modifier =Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .width(161.dp)
                            .fillMaxHeight()
                            .padding(start = 2.dp, top = 2.dp, end = 2.dp, bottom = 2.dp)
                            .clickable { navController.navigate("register") }
                    ) {
                        Text(
                            text = "Log In",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF232447),
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }

                }

                Spacer(modifier = Modifier.height(40.dp))


                Text(
                    text = "Full Name",
                    style = TextStyle(
                        fontFamily = montserratFont,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(500.dp),
                    value = fullname,
                    onValueChange = {
                        fullname = it
                    },
                    placeholder = {
                        Text(
                            text = "Full Name",

                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                color = Color(0xFF757575),
                            )
                        )
                    },
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

                Text(
                    text = "Username",
                    style = TextStyle(
                        fontFamily = montserratFont,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Black
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
                    placeholder = {
                        Text(
                            text = "Username",

                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                color = Color(0xFF757575),
                            )
                        )
                    },
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

                Text(
                    text = "Email",
                    style = TextStyle(
                        fontFamily = montserratFont,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Black
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
                    placeholder = {
                        Text(
                            text = "Email Address",

                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                color = Color(0xFF757575),
                            )
                        )
                    },
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

                Text(
                    text = "Password",
                    style = TextStyle(
                        fontFamily = montserratFont,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    color = Color.Black
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
                    placeholder = {
                        Text(
                            text = "Password",

                            // Body Text/Body Small Medium
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 19.6.sp,
                                fontFamily = montserratFont,
                                color = Color(0xFF757575),
                            )
                        )
                    },
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

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .shadow(elevation = 2.dp, spotColor = Color(0x3DE4E5E7), ambientColor = Color(0x3DE4E5E7))
                        .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(size = 10.dp))
                        .width(332.dp)
                        .height(46.dp)
                        .background(color = Color(0xFF1A5294), shape = RoundedCornerShape(size = 10.dp))
                        .padding(horizontal = 14.dp)
                        .clickable {
                            if (validatePassword(password)) {
                                Log.d("SignUpScreen", "Image clicked")
                                // backend
                                authViewModel?.signIn(
                                    username = username,
                                    password = password,
                                    email = userEmail,
                                    fullName = fullname // Fixx
                                )
                            } else {
                                isPasswordValid = false
                                passwordError = "Password must be at least 6 characters long"
                            }
                        }
                ) {
                    Text(
                        text = "Sign Up",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 19.6.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    )
                }
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
    context: Context
) {
    SignUpScreen(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
        context = context
    )
}