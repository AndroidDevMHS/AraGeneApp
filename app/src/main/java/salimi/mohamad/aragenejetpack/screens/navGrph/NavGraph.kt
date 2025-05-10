package salimi.mohamad.aragenejetpack.screens.navGrph

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import salimi.mohamad.aragenejetpack.R
import salimi.mohamad.aragenejetpack.data.model.BtnNavItem
import salimi.mohamad.aragenejetpack.screens.About
import salimi.mohamad.aragenejetpack.screens.AccountScreen
import salimi.mohamad.aragenejetpack.screens.AparatView
import salimi.mohamad.aragenejetpack.screens.Article
import salimi.mohamad.aragenejetpack.screens.ArticleTxtShow
import salimi.mohamad.aragenejetpack.screens.FahliCheckList
import salimi.mohamad.aragenejetpack.screens.FahliMainHelp
import salimi.mohamad.aragenejetpack.screens.Home
import salimi.mohamad.aragenejetpack.screens.Planner
import salimi.mohamad.aragenejetpack.screens.SplashScreen
import salimi.mohamad.aragenejetpack.screens.SuperMixFormulaScreen
import salimi.mohamad.aragenejetpack.screens.SuperMixScreen
import salimi.mohamad.aragenejetpack.screens.VideoShow
import salimi.mohamad.aragenejetpack.screens.login.LoginScreen
import salimi.mohamad.aragenejetpack.screens.login.OtpAuthScreen
import salimi.mohamad.aragenejetpack.ui.theme.Cream
import salimi.mohamad.aragenejetpack.ui.theme.Meshki
import salimi.mohamad.aragenejetpack.viewModel.DataStoreViewModel
import salimi.mohamad.aragenejetpack.viewModel.FahliCheckDbViewModel
import salimi.mohamad.aragenejetpack.viewModel.PlannerViewModel
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel
import salimi.mohamad.aragenejetpack.viewModel.SuperMixViewModel
import salimi.mohamad.aragenejetpack.viewModel.VideoUrlViewModel

@Composable
fun SetupNavGraph(
    context: Context,
    viewModelSms: SmsViewModel,
    viewModelDataStore: DataStoreViewModel,
    viewModelDataBase: FahliCheckDbViewModel,
    viewModelPlanner: PlannerViewModel,
    viewModelVideoUrl: VideoUrlViewModel,
    viewModelSuperMix: SuperMixViewModel
) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var badge by remember { mutableStateOf(false) }
    val currentDestination = navBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
        viewModelPlanner.allMessage.collectLatest { itemList ->
            badge = itemList.any { !it.done }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Scaffold(
            bottomBar = {
                if (currentDestination in listOf(
                        Screens.Home.route,
                        Screens.About.route,
                        Screens.Account.route,
                        Screens.Planner.route
                    )
                ) {
                    BottomNavigationBar(navController = navController, badge)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screens.Splash.route,
                modifier = Modifier
                    .padding(innerPadding)
                    .background(Color.White)
            ) {
                composable(route = Screens.Splash.route) {
                    SplashScreen(viewModelDataStore, navController)
                }
                composable(route = Screens.Home.route) {
                    Home(navController)
                }
                composable(route = Screens.About.route) {
                    About()
                }
                composable(route = Screens.Account.route) {
                    AccountScreen(navController, viewModelSms, viewModelDataStore)
                }
                composable(route = Screens.FahliMainScreen.route) {
                    FahliMainHelp()
                }
                composable(route = Screens.Login.route) {
                    LoginScreen(navController, viewModelSms, context)
                }
                composable(
                    route = "${Screens.OtpAuth.route}/{phoneNumber}/{otp}",
                    arguments = listOf(
                        navArgument("phoneNumber") { type = NavType.StringType },
                        navArgument("otp") { type = NavType.IntType }
                    )
                ) { navBackStackEntry ->
                    val phoneNumber = navBackStackEntry.arguments?.getString("phoneNumber")
                    val otp = navBackStackEntry.arguments?.getInt("otp")
                    if (phoneNumber != null && otp != null) {
                        OtpAuthScreen(
                            viewModel = viewModelSms,
                            phoneNumber = phoneNumber,
                            otp = otp,
                            onLoginSuccess = {
                                coroutineScope.launch {
                                    viewModelDataStore.saveLoginState(true)
                                    viewModelDataStore.savePhoneNumber(phoneNumber)
                                    navController.navigate(Screens.Home.route) {
                                        popUpTo(0) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    }
                }
                composable(route = Screens.FahliCheckBox.route) {
                    FahliCheckList(context, viewModelDataStore, viewModelDataBase)
                }
                composable(
                    route = Screens.Planner.route,
                    deepLinks = listOf(navDeepLink {
                        uriPattern = "app://${Screens.Planner.route}"
                    })
                ) {
                    Planner(
                        navController = navController,
                        viewModelSms = viewModelSms,
                        viewModelPlanner = viewModelPlanner,
                        viewModelDataStore = viewModelDataStore,
                        context = context
                    )
                }
                composable(route = Screens.VideoShow.route) {
                    VideoShow(navController, viewModel = viewModelVideoUrl)
                }
                composable(route = Screens.Article.route) {
                    Article(navController, viewModelVideoUrl)
                }
                composable(route = Screens.SuperMix.route) {
                    SuperMixScreen(viewModelSuperMix)
                }
                composable(route = Screens.ArticleTxtShow.route) {
                    ArticleTxtShow(viewModelVideoUrl)
                }
                composable(route = Screens.SuperMixCalculator.route) {
                    SuperMixFormulaScreen()
                }
                composable(
                    route = "${Screens.ShowAparatScreen.route}/{URL}",
                    arguments = listOf(navArgument("URL") { type = NavType.StringType })
                ) { navBackStackEntry ->
                    val url = navBackStackEntry.arguments?.getString("URL") ?: ""
                    AparatView(navController = navController, videoUrl = url)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, badge: Boolean) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    val items = listOf(
        BtnNavItem(
            "تماس با ما",
            Screens.About.route,
            Icons.Rounded.Info,
            Icons.Outlined.Info,
            false
        ),
        BtnNavItem(
            "اقدامات",
            Screens.Planner.route,
            Icons.Rounded.DateRange,
            Icons.Outlined.DateRange,
            badge
        ),
        BtnNavItem("خانه", Screens.Home.route, Icons.Rounded.Home, Icons.Outlined.Home, false),
        BtnNavItem(
            "حساب کاربری",
            Screens.Account.route,
            Icons.Rounded.AccountCircle,
            Icons.Outlined.AccountCircle,
            false
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 8.dp)
            .shadow(4.dp, shape = RoundedCornerShape(30.dp), clip = false) // سایه خارج از کلیپ
            .background(Color.White, shape = RoundedCornerShape(30.dp))   // پس‌زمینه برای نمایش سایه
            .clip(RoundedCornerShape(30.dp)),                              // کلیپ جداگانه
        contentAlignment = Alignment.BottomCenter,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(R.color.blue_logo).copy(alpha = 0.2f),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(horizontal = 10.dp, vertical = 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { btnNavItem ->
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate(route = btnNavItem.route) {
                                popUpTo(Screens.Home.route) { inclusive = false }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = if (currentDestination == btnNavItem.route)
                                btnNavItem.selectedIcon else btnNavItem.unSelectedIcon,
                            contentDescription = btnNavItem.title,
                            tint = if (currentDestination == btnNavItem.route)
                                colorResource(R.color.blue2_logo) else Meshki,
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = btnNavItem.title,
                            fontSize = 16.sp,
                            color = if (currentDestination == btnNavItem.route)
                                colorResource(R.color.blue2_logo) else Meshki,
                        )
                    }
                    if (btnNavItem.hasNews) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .padding(1.dp)
                                .background(color = colorResource(R.color.pumpkin), CircleShape)
                                .align(Alignment.TopEnd)
                        )
                    }
                }
            }
        }
    }
}
