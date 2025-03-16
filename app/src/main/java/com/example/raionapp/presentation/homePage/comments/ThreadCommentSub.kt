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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.raionapp.R
import kotlinx.coroutines.CoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.RectangleShape
import com.example.raionapp.firestore.CommentCollection
import com.example.raionapp.firestore.ThreadCollection
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
    commentId: String
) {
    val commentCollection = CommentCollection()
    val coroutineScope = rememberCoroutineScope()

//    Perihal like
    var isLiked by remember { mutableStateOf(false) }
    var likeCount by remember { mutableIntStateOf(numberOfLike) }

    Box(
        Modifier.fillMaxSize()
    ) {
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
                Row(
                    modifier = Modifier.offset(x = -60.dp)
                ) {
                    //                Icon(
                    //                    painter = painterResource(id = R.drawable.heading_small_circle),
                    //                    contentDescription = null,
                    //                    modifier = Modifier
                    //                        .size(50.dp)
                    //                )

                    //                User Profile Picture
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

                    Column(
                        modifier = Modifier
                            .padding(10.dp, 0.dp)
                    ) {
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

                /*Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {

                }*/

                Row(
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp, 30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Transparent) // Background color to match button
                            .clickable {
                                coroutineScope.launch {
//                                    if (isLiked == false) {
//                                        likeCount++
//                                    } else {
//                                        likeCount--
//                                    }
//                                    isLiked = !isLiked
//
//                                    val updateThread = mapOf("numberOfLike" to likeCount)
//                                    commentCollection.updateComment(
//                                        threadId = threadId,
//                                        commentId = commentId,
//                                        updateData = updateThread
//                                    )
                                }
                            },
                        contentAlignment = Alignment.CenterEnd // Align text to the right
                    ) {
                        Text(
                            text = numberOfLike.toString(),
                            fontSize = 12.sp,
                            color = Color.Black, // Text color for contrast
                            modifier = Modifier.padding(end = 10.dp) // Adjust padding if needed
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.heart_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 8.dp),
                            tint = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.padding(5.dp))

                    Box(
                        modifier = Modifier
                            .size(34.dp, 30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Transparent) // Background color to match button
                            .clickable { /* Handle Click Here */ },
                        contentAlignment = Alignment.CenterEnd // Align text to the right
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.bookmark_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center),
                            tint = Color.Black
                        )

                    }

                    Spacer(modifier = Modifier.padding(5.dp))

                    Box(
                        modifier = Modifier
                            .size(34.dp, 30.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Transparent) // Background color to match button
                            .clickable { /* Handle Click Here */ },
                        contentAlignment = Alignment.CenterEnd // Align text to the right
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.share_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center),
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}
