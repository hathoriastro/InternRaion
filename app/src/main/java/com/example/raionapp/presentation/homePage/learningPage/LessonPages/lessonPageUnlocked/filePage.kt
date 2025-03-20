package com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R
import com.example.raionapp.common.montserratFont
import com.example.raionapp.presentation.homePage.TopBarAndProfile
import com.example.raionapp.presentation.homePage.threads.ThreadContent
import com.example.raionapp.presentation.register.AuthViewModel

@Composable
fun FilePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel?,
    subLessonFile: String
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        TopBarAndProfile(
            modifier = modifier,
            navController = navController,
            authViewModel = authViewModel,
        )
        Column(
            Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
                    .fillMaxSize()
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            ) {
                Row(
                    modifier = Modifier
                        .offset(20.dp, 20.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Back",

                        // Body Text/Body 1 Medium
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 22.4.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF000000),
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(0.92f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Column( //DISINI UNTUK MENARUH VIDEO
                        modifier = Modifier
                            .shadow(elevation = 8.dp, RoundedCornerShape(16.dp))
                            .background(Color.White, RoundedCornerShape(16.dp))
                            .fillMaxWidth(0.9f)
                            .height(229.dp)
                    ){
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(45.dp)
                                .background(Color(0xFFFDCB1A)),
                        ) {
                            Text(
                                text = "Website Design. pdf",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 19.6.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFFFFFFF),
                                ),
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .offset(x = 20.dp)

                            )

                            Icon(
                                painter = painterResource(id = R.drawable.file_page_extendview),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .offset(x = -20.dp)
                                    .clickable {

                                    },
                                tint = Color.White
                            )
                        }
                        Column(
                            Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = "1. Pengertian Website Design\nWebsite design adalah proses merancang tampilan dan pengalaman pengguna (UI/UX) pada sebuah situs web agar fungsional, menarik, dan mudah digunakan.\n2. Elemen Utama Website Design\nLayout: Struktur halaman yang mengatur elemen seperti teks, gambar, dan navigasi.\nWarna: Menentukan identitas dan suasana situs. Pilih palet warna yang sesuai dengan branding.\nTipografi: Gunakan font yang mudah dibaca dan konsisten.\nNavigasi: Buat menu yang jelas dan mudah diakses.\nResponsiveness: Desain harus dapat menyesuaikan tampilan di berbagai perangkat (mobile-friendly).\nVisual & Multimedia: Gunakan gambar dan video\nTren Website Design 2024\nDark Mode: Memberikan pengalaman visual yang lebih nyaman.\nMinimalist Design: Desain sederhana dengan elemen fungsional.\n",
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    lineHeight = 15.5.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF7D7F83),
                                )
                            )
                        }

                    }
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize(0.9f),
                        verticalArrangement = Arrangement.spacedBy(30.dp)
                    ){
                        Column {
                            Text(
                                text = "Website Design",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    lineHeight = 30.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF1E1E1E),
                                )
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.mentor_icon_human),
                                    contentDescription = null,
                                    tint = Color.Gray
                                )

                                Text(
                                    text = "By Robert James",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp,
                                        fontFamily = montserratFont,
                                        fontWeight = FontWeight(400),
                                        color = Color.Gray,
                                    ),
                                    modifier = Modifier.padding(start = 7.dp)
                                )
                            }
                        }
                        Text(
                            text = "Lorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a. \n\nLorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )

                        Text(
                            text = "Lorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a. \n\nLorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )

                        Text(
                            text = "Lorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a. \n\nLorem ipsum dolor sit amet consectetur. Urna integer volutpat ullamcorper in. Sed interdum ultricies mi habitant sagittis mauris. Venenatis libero malesuada viverra cras ullamcorper. Lacus dignissim semper ultrices ornare a.",
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.6.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
private fun FilePagePreview(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel? = null,
) {
    FilePage(
        modifier = modifier,
        navController = navController,
        authViewModel = authViewModel,
        subLessonFile = ""
    )

}

