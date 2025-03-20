package com.example.raionapp.presentation.homePage.learningPage

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.authentication.AuthViewModel

@Composable
fun LearningContentRow(
    modifier: Modifier = Modifier,
    subjectName: String,
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {
    Row {
        Text(
            text = subjectName,
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 27.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(600),
                color = Color(0xFF212121),
            ),
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ){
        LearningContent(
            "Kedokteran",
            "Biologi",
            "Jamal Michael",
            1000,
            navController = navController,
            authViewModel = authViewModel
        )
    }
}