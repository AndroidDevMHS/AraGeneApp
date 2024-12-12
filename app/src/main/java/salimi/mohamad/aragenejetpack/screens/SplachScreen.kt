/*
package salimi.mohamad.aragenejetpack.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.data.isLoggedIn
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens

@Composable
fun SplashScreen(navController: NavController, context: Context) {
    val coroutineScope = rememberCoroutineScope()
    val isLoggedInState = remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val isLoggedIn = context.isLoggedIn().first()
            isLoggedInState.value = isLoggedIn
        }
    }

    when (isLoggedInState.value) {
        true -> {
            navController.navigate(Screens.HomeScreen.route) {
                popUpTo("splash") { inclusive = true }
            }
        }
        false -> {
            navController.navigate(Screens.Login.route) {
                popUpTo("splash") { inclusive = true }
            }
        }
        null -> {
            // نمایش یک انیمیشن یا پیام انتظار
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
*/
