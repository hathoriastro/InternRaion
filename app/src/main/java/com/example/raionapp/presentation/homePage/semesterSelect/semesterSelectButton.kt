package com.example.raionapp.presentation.homePage.semesterSelect

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.register.AuthViewModel

@Composable
fun SemesterSelectButton(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel?,
    semester : Int
) {
    val stringsemester : String = semester.toString()
    Box(
        Modifier
            .shadow(elevation = 2.dp, spotColor = Color(0x3DE4E5E7), ambientColor = Color(0x3DE4E5E7))
            .border(width = 1.dp, color = Color(0xFF5598CC), shape = RoundedCornerShape(size = 10.dp))
            .width(394.dp)
            .height(46.dp)
            .clickable {
                //Clickable Disini
            }
    ){
        Text(
            text = "Semester $stringsemester",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
            ),
            modifier = Modifier.align(Alignment.Center)
        )

    }
}

@Preview
@Composable
private fun SemesterSelectButtonPreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    SemesterSelectButton(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
        semester = 3
    )
}