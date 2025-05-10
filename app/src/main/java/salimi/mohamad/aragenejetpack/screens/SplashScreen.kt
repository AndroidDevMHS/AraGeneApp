package salimi.mohamad.aragenejetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.screens.navGrph.Screens
import salimi.mohamad.aragenejetpack.viewModel.DataStoreViewModel

@Composable
fun SplashScreen(viewModelDataStore: DataStoreViewModel, navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("anime.json"))
    val progress by animateLottieCompositionAsState(composition)
    val loginState = viewModelDataStore.getUserLoginState().let { it ->
        if (it) Screens.Home.route else Screens.Login.route
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(250.dp).align(Alignment.Center)
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate(loginState) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
            launchSingleTop = true
        }
    }
}