import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
import com.example.raionapp.presentation.homePage.threads.ThreadContent
import com.example.raionapp.presentation.homePage.ThreadCommentAdd
import com.example.raionapp.presentation.learningPage.learningPageHome.BankPageHome
import com.example.raionapp.presentation.homePage.learningPage.LearningPageHome
import com.example.raionapp.presentation.homePage.learningPage.LessonPages.ReviewPageBox
import com.example.raionapp.presentation.learningPage.learningPageHome.AboutPage
import com.example.raionapp.presentation.learningPage.learningPageHome.LessonPage
import com.example.raionapp.presentation.learningPage.learningPageHome.ReviewPage
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
        composable("lessonpage") {
            LessonPage(modifier,navController, authViewModel)
        }
        composable("reviewpage") {
            ReviewPage(modifier,navController, authViewModel)
        }
        composable("aboutpage") {
            AboutPage(modifier,navController, authViewModel)
        }
    }
}