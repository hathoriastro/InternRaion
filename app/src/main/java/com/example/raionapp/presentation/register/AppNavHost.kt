import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.raionapp.presentation.register.ForgotPasswordScreen
import com.example.raionapp.presentation.register.RegisterScreen
import com.example.raionapp.presentation.register.SignUpScreen
import com.example.raionapp.presentation.register.VerifyScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController() // ✅ Create navController instance

    NavHost(
        navController = navController, // ✅ Pass it here
        startDestination = "register",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("register") { RegisterScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("forgotpassword") { ForgotPasswordScreen(navController) }
        composable("verifyscreen") { VerifyScreen(navController) }
    }
}

