package com.example.raionapp.presentation.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    // Get current route from NavController
    val currentRoute = navController.currentDestination?.route ?: "home"

    Row(
        modifier = Modifier
            .background(
                color = Color(0xFFFDCB1A),
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            )
            .fillMaxWidth()
            .height(70.dp)
            .zIndex(3f),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavItem("home", R.drawable.home_icon_unclicked, R.drawable.home_icon_clicked,"Home", currentRoute, navController)
        NavItem("bank", R.drawable.bank_soal_icon_unclicked_coba, R.drawable.bank_soal_icon_clicked,"Bank", currentRoute, navController)
        NavItem("learningpage", R.drawable.learn_icon_unclicked, R.drawable.learn_icon_clicked,"Learn", currentRoute, navController)
        NavItem("profile", R.drawable.profile_icon_unclicked, R.drawable.profile_icon_clicked,"Profile", currentRoute, navController)
    }
}

@Composable
fun NavItem(
    id: String,
    iconRes: Int, // Unselected icon resource
    selectedIconRes: Int, // Selected icon resource
    label: String,
    currentRoute: String,
    navController: NavHostController
) {
    val isSelected = currentRoute == id
    val textColor = if (isSelected) Color(0xFF1A5294) else Color.Black
    val iconTint = if (isSelected) Color(0xFF1A5294) else Color.Black

    Column(
        modifier = Modifier
            .clickable { navController.navigate(id) }
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Use the selected or unselected icon based on the state
        Icon(
            painter = painterResource(id = if (isSelected) selectedIconRes else iconRes),
            contentDescription = null,
            tint = iconTint
        )

        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight(500),
                color = textColor,
                textAlign = TextAlign.Center,
            )
        )
    }
}
