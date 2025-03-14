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
import com.example.raionapp.presentation.homePage.AddThreadPage
import com.example.raionapp.presentation.homePage.HomePageScreen
import com.example.raionapp.presentation.profile.ProfilePage

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController() // ✅ Create navController instance

    NavHost(
        navController = navController, // ✅ Pass it here
        startDestination = "home",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("register") {
            LoginScreen(modifier,navController, authViewModel)
        }
        composable("signup") {
            SignUpScreen(modifier,navController, authViewModel)
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
        composable("profile") {
            ProfilePage(modifier,navController, authViewModel)
        }
        composable("addthread") {
            AddThreadPage(modifier,navController, authViewModel)
        }
    }
}