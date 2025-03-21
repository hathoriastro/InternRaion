package com.example.raionapp.presentation.homePage.learningPage

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.raionapp.common.montserratFont
import androidx.navigation.compose.rememberNavController
import com.example.raionapp.firestore.model.LessonDataClass

@Composable
fun LearningContentRow(
    modifier: Modifier = Modifier,
    subjectName: String,
    lessonData: List<Pair<String, LessonDataClass>>,
    navController: NavHostController,
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
            modifier = modifier.padding(vertical = 20.dp)
        )
    }

    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        lessonData.forEach { (lessonId, lessonData) ->
            LearningContent(
                lessonId = lessonId,
                subjectName = lessonData.subject,
                lessonName = lessonData.lessonName,
                mentorName = lessonData.mentorName,
                mentorProfilePicture = lessonData.mentorProfilePicture,
                likeCount = lessonData.likeCount,
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LearningContentRowPreview() {
    LearningContentRow(
        subjectName = "Kedokteran",
        navController = rememberNavController(),
        lessonData = emptyList(),
    )
}