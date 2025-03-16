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
import androidx.credentials.CredentialManager

// Import Backend AuthViewModel
import com.example.raionapp.presentation.authentication.AuthViewModel
import com.example.raionapp.presentation.homePage.AddThreadPage
import com.example.raionapp.presentation.homePage.HomePageScreen
import com.example.raionapp.presentation.homePage.ThreadComment
import com.example.raionapp.presentation.learningPage.learningPageHome.LearningPageHome
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
        startDestination = "comment",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("register") {
            LoginScreen(modifier,navController, authViewModel, context)
        }
        composable("signup") {
            SignUpScreen(modifier,navController, authViewModel, context)
        }
        composable("home") {
            HomePageScreen(modifier,navController, authViewModel, context)
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
        composable("comment") {
            ThreadComment(modifier,navController, authViewModel, context)
        }
    }
}