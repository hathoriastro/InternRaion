import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.raionapp.mentorship.CreateNewClassPage
import com.example.raionapp.mentorship.MyCoursePage
import com.example.raionapp.presentation.register.nantiAja.ForgotPasswordScreen
import com.example.raionapp.presentation.register.LoginScreen
import com.example.raionapp.presentation.register.SignUpScreen
import com.example.raionapp.presentation.register.nantiAja.VerifyScreen

// Import Backend AuthViewModel
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.threads.AddThreadPage
import com.example.raionapp.presentation.homePage.HomePageScreen
import com.example.raionapp.presentation.homePage.SavedAnswers
import com.example.raionapp.presentation.homePage.comments.ThreadComment
import com.example.raionapp.presentation.homePage.comments.ThreadCommentAdd
import com.example.raionapp.presentation.homePage.bankPage.BankPageHome
import com.example.raionapp.presentation.homePage.bankPage.BankPageList
import com.example.raionapp.presentation.homePage.learningPage.LearningPageHome
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.ChatPage
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.FilePage
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.LessonPageUnlocked
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.ModulListPage
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.PaymentOverviewPage
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.VideoListPage
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.VideoPage
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.lessonPageUnlocked.VideoPracticeListPage
import com.example.raionapp.presentation.homePage.semesterSelect.SemesterSelectPage
import com.example.raionapp.presentation.homePage.subjectSelect.SubjectSelectPage
import com.example.raionapp.presentation.learningPage.learningPageHome.AboutPage
import com.example.raionapp.presentation.learningPage.learningPageHome.LessonPage
import com.example.raionapp.presentation.learningPage.learningPageHome.ReviewPage
import com.example.raionapp.presentation.profile.AboutProfilePage
import com.example.raionapp.presentation.profile.BecomeAMentorPage
import com.example.raionapp.presentation.profile.MentorRegistrationPage
import com.example.raionapp.presentation.profile.PostedQuestionsPage
import com.example.raionapp.presentation.profile.ProfilePage

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    context: Context
) {
    val navController = rememberNavController() // ✅ Create navController instance

    NavHost(
        navController = navController, // ✅ Pass it here
        startDestination = "register",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("register") {
            LoginScreen(modifier,navController, authViewModel, context)
        }
        composable("signup") {
            SignUpScreen(modifier,navController, authViewModel, context)
        }
        composable("home") {
            HomePageScreen(modifier,navController, authViewModel)
        }
        composable("forgotpassword") {
            ForgotPasswordScreen(modifier,navController, authViewModel)
        }
        composable("verif") {
            VerifyScreen(modifier,navController, authViewModel)
        }
        composable("learningpage") {
            LearningPageHome(modifier,navController, authViewModel)
        }
        composable("profile") {
            ProfilePage(modifier,navController, authViewModel, context)
        }
        composable("addthread") {
            AddThreadPage(modifier,navController, authViewModel)
        }
        composable("addcomment/{threadId}")  { backStackEntry ->
            // Ambil threadId dari argument rute
            val threadId = backStackEntry.arguments?.getString("threadId") ?: ""
            ThreadCommentAdd(modifier, navController, authViewModel, threadId)
        }
        composable("comment/{threadId}") { backStackEntry ->
            // Ambil threadId dari argument rute
            val threadId = backStackEntry.arguments?.getString("threadId") ?: ""
            ThreadComment(modifier, navController, authViewModel, threadId)
        }
        composable("bank") {
            BankPageHome(modifier,navController, authViewModel)
        }
        composable("savedanswers") {
            SavedAnswers(modifier,navController, authViewModel)
        }
        composable("postedquestions") {
            PostedQuestionsPage(modifier,navController)
        }
        composable("lessonpage") { //String diisi dengan nama mentor
            LessonPage("Contoh", modifier,navController, authViewModel)
        }
        composable("reviewpage") {
            ReviewPage(modifier,navController, authViewModel)
        }
        composable("aboutpage") {
            AboutPage(modifier,navController, authViewModel)
        }
        composable("subjectselectpage") {
            SubjectSelectPage(modifier,navController, authViewModel)
        }
        composable("semesterselectpage") {
            SemesterSelectPage(modifier,navController, authViewModel)
        }
        composable("aboutprofilepage") {
            AboutProfilePage(modifier,navController)
        }
        composable("lessonpageunlocked") {
            LessonPageUnlocked("Contoh Budi", modifier,navController, authViewModel)
        }
        composable("videopage") {
            VideoPage(modifier, navController, authViewModel)
        }
        composable("filepage") {
            FilePage(modifier, navController, authViewModel)
        }
        composable("chatpage") {
            ChatPage(modifier, navController, authViewModel)
        }
        composable("videolistpage") {
            VideoListPage(modifier, navController, authViewModel)
        }
        composable("mentorregistration") {
            MentorRegistrationPage(modifier,navController)
        }
        composable("becomeamentorpage") {
            BecomeAMentorPage(modifier,navController)
        }
        composable("mycourse") {
            MyCoursePage(modifier, navController, authViewModel)
        }
        composable("addnewclass") {
            CreateNewClassPage(modifier, navController)
        }
        composable("modullistpage") {
            ModulListPage(modifier, navController, authViewModel)
        }
        composable("videopracticelistpage") {
            VideoPracticeListPage(modifier, navController, authViewModel)
        }
        composable("banklistpage") {
            BankPageList(modifier, navController)
        }
        composable("paymentoverview") {
            PaymentOverviewPage(modifier, navController, authViewModel)
        }

    }
}