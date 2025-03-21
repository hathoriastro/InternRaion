package com.example.raionapp.presentation.homePage.bankPage

import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.ChatBubbleMentor
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.ChatBubbleUser
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.raionapp.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.authentication.AuthViewModel

@Composable
fun BankPageList(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val keyboardHeight = WindowInsets.ime.asPaddingValues().calculateBottomPadding() // Detect keyboard height
    var comment by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Header Box - Moves down when the keyboard appears
        Column(
            modifier = Modifier
                .offset(y = keyboardHeight - 10.dp)
        ) {
            Box(
                Modifier
                    .zIndex(3f)
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(color = Color(0xFF5598CC), shape = RoundedCornerShape(size = 24.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow_icon),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterStart)
                        .padding(start = 40.dp, top = 20.dp)
                        .clickable { navController.popBackStack() },
                    tint = Color.White
                )

                Text(
                    text = "Pemrograman Dasar",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight(700),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 20.dp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(
                        bottom = 45.dp
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = -keyboardHeight)
                        .padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                ) { //COLUMN KONTEN CHAT
                    Spacer(modifier = Modifier.height(10.dp))
                    BankListContent("Pemrograman Lanjut")
                    BankListContent("Pemrograman Python")
                    BankListContent("Pemrograman Kotlin")
                    BankListContent("Pemrograman Java")
                    BankListContent("Pemrograman html")
                }
            }
        }
    }
}


@Preview
@Composable
private fun BankPageListPreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null
) {
    BankPageList(
        modifier = modifier,
        navController = navController
    )
}