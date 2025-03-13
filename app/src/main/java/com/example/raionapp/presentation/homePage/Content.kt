package com.example.raionapp.presentation.homePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.R

@Composable
fun ContentScreen(UserName: String, UserTag: String, modifier: Modifier = Modifier) {
    val commentcount = 0
    val likecount = 0
    Box(modifier = Modifier
        .height(400.dp)
        .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(300.dp)
                .fillMaxHeight()
                .padding(top = 30.dp)
        ){
            Row(
                modifier = Modifier
            ){
                Icon(
                    painter = painterResource(id = R.drawable.heading_small_circle),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                ) {
                    Text(
                        text = UserName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "@" + UserTag,
                        color = Color(0xFFA7A7A7)
                    )
                }

            }

            Text(
                "Ini pertanyaan",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                fontSize = 18.sp,
                color = Color.Black
            )

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {

            }

            Row(){
                Box(
                    modifier = Modifier
                        .size(60.dp, 30.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFA7A7A7)) // Background color to match button
                        .clickable { /* Handle Click Here */ },
                    contentAlignment = Alignment.CenterEnd // Align text to the right
                ) {
                    Text(
                        text = commentcount.toString(),
                        fontSize = 12.sp,
                        color = Color.Black, // Text color for contrast
                        modifier = Modifier.padding(end = 10.dp) // Adjust padding if needed
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.comment_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 10.dp),
                        tint = Color.Black

                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Box(
                    modifier = Modifier
                        .size(80.dp, 30.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFA7A7A7)) // Background color to match button
                        .clickable { /* Handle Click Here */ },
                    contentAlignment = Alignment.CenterEnd // Align text to the right
                ) {
                    Text(
                        text = likecount.toString(),
                        fontSize = 12.sp,
                        color = Color.Black, // Text color for contrast
                        modifier = Modifier.padding(end = 10.dp) // Adjust padding if needed
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.heart_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 10.dp),
                        tint = Color.Black
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Box(
                    modifier = Modifier
                        .size(34.dp, 30.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFA7A7A7)) // Background color to match button
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
                        .background(Color(0xFFA7A7A7)) // Background color to match button
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
    Divider()
}
