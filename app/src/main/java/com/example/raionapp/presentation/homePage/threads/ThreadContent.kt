package com.example.raionapp.presentation.homePage.threads

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.raionapp.firestore.ThreadCollection
import com.example.raionapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.raionapp.presentation.homePage.model.ThreadViewModel
import kotlinx.coroutines.launch

@Composable
fun ThreadContent(
    threadId: String,
    fullname: String,
    username: String,
    profilePicture: String?,
    text: String,
    numberOfComment: Int,
    numberOfLike: Int,
    isLiked: Boolean,
    modifier: Modifier = Modifier,
    navController: NavHostController?
) {
    val threadViewModel: ThreadViewModel = viewModel()
    val threadCollection = ThreadCollection()
    val coroutineScope = rememberCoroutineScope()

    var isLiked by remember { mutableStateOf(isLiked) }
    var likeCount by remember { mutableIntStateOf(numberOfLike) }
    var isSaved by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { navController?.navigate("comment/$threadId") }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.8f)
                .padding(top = 30.dp)
        ) {
            Row {
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
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "@$username",
                        fontSize = 11.sp,
                        color = Color(0xFF565656)
                    )
                }
            }
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .clickable { navController?.navigate("comment/$threadId") }
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.comment_icon),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = numberOfComment.toString(),
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Row(
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            likeCount += if (!isLiked) 1 else -1
                            isLiked = !isLiked
                            threadCollection.updateThreadFirestore(threadId, mapOf(
                                "numberOfLike" to likeCount,
                                "isLiked" to isLiked
                            ))
                        }
                    }
                        .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
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
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier.clickable { isSaved = !isSaved }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark_icon),
                        contentDescription = null,
                        tint = if (isSaved) Color(0xFF5598CC) else Color.Black
                    )
                }
            }
        }
    }
    Divider()
}
