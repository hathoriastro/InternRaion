package com.example.raionapp.presentation.homePage.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.raionapp.R
import com.example.raionapp.presentation.homePage.model.CommentViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raionapp.firestore.CommentCollection
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import com.example.raionapp.common.montserratFont

@Composable
fun ThreadCommentSub(
    threadId: String,
    fullname: String,
    username: String,
    profilePicture: String?,
    text: String,
    numberOfLike: Int,
    modifier: Modifier = Modifier,
    commentId: String,
    isLiked: Boolean
) {
    // Ambil instance CommentViewModel sehingga kita bisa menggunakan fungsi increase/decrease like
    val commentViewModel: CommentViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()

    // State untuk status like dan jumlah like
    var isLiked by remember { mutableStateOf(isLiked) }
    var likeCount by remember { mutableIntStateOf(numberOfLike) }

    Box(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterEnd)
        ) {
            VerticalDivider(
                modifier = Modifier
                    .height(160.dp) // Ensure it fills available height
                    .width(1.dp),     // Set thickness
                color = Color.Gray   // Optional: Change color
            )
            Column(
                modifier = Modifier
                    .padding(top = 30.dp, start = 20.dp)
            ) {
                Row(
                    modifier = modifier
                        .offset(x = -45.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = profilePicture,
                            placeholder = painterResource(R.drawable.heading_small_circle),
                            error = painterResource(R.drawable.heading_small_circle)
                        ),
                        contentDescription = "Profile Picture",
                        modifier = modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Column(modifier = Modifier.padding(10.dp, 0.dp)) {
                        Text(
                            text = fullname,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                            )
                        )
                        Text(
                            text = "@" + username,
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF565656),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Transparent)
                            .clickable {
                                coroutineScope.launch {
                                    if (isLiked == false) {
                                        commentViewModel.increaseCommentLike(threadId, commentId)
                                        likeCount++
                                    } else {
                                        commentViewModel.decreaseCommentLike(threadId, commentId)
                                        likeCount--
                                    }
                                    isLiked = !isLiked
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart_icon),
                            contentDescription = null,
                            tint = if (isLiked) Color.Red else Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = likeCount.toString(),
                            fontSize = 12.sp,
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .clickable {},
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark_icon),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            }
            HorizontalDivider(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}