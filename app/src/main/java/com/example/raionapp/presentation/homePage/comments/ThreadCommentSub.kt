package com.example.raionapp.presentation.homePage.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.raionapp.R
import com.example.raionapp.presentation.homePage.model.CommentViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

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

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(0.9f)
                .border(0.5.dp, Color(0xFFA7A7A7), RectangleShape)
                .align(Alignment.CenterEnd)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(300.dp)
                    .fillMaxHeight()
                    .padding(top = 30.dp)
            ) {
                Row(modifier = Modifier.offset(x = -60.dp)) {
                    // Menampilkan foto profil
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = profilePicture,
                            placeholder = painterResource(R.drawable.heading_small_circle),
                            error = painterResource(R.drawable.heading_small_circle)
                        ),
                        contentDescription = "Foto Profil Comment",
                        modifier = modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )
                    Column(modifier = Modifier.padding(10.dp, 0.dp)) {
                        Text(
                            text = fullname,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = "@$username",
                            color = Color(0xFFA7A7A7)
                        )
                    }
                }
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 3.dp)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Row(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)) {
                    // Box untuk like; saat diklik, fungsi di CommentViewModel akan dipanggil.
                    Box(
                        modifier = Modifier
                            .size(80.dp, 30.dp)
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
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = likeCount.toString(),
                            fontSize = 12.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.heart_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 8.dp),
                            tint = if(isLiked) Color.Red else Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    // Box untuk bookmark
                    Box(
                        modifier = Modifier
                            .size(34.dp, 30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Transparent)
                            .clickable { /* Handle Bookmark Click Here */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark_icon),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    // Box untuk share
                    Box(
                        modifier = Modifier
                            .size(34.dp, 30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Transparent)
                            .clickable { /* Handle Share Click Here */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.share_icon),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}
