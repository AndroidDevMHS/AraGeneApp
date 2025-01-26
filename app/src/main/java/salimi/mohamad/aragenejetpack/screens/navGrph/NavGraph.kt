package salimi.mohamad.aragenejetpack.screens.navGrph

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import salimi.mohamad.aragenejetpack.screens.SuperMixFormulaScreen
import salimi.mohamad.aragenejetpack.screens.SuperMixScreen
import salimi.mohamad.aragenejetpack.screens.VideoShow
import salimi.mohamad.aragenejetpack.screens.login.LoginScreen
import salimi.mohamad.aragenejetpack.screens.login.OtpAuthScreen
import salimi.mohamad.aragenejetpack.viewModel.DataStoreViewModel
import salimi.mohamad.aragenejetpack.viewModel.FahliCheckDbViewModel
import salimi.mohamad.aragenejetpack.viewModel.PlannerViewModel
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel
import salimi.mohamad.aragenejetpack.viewModel.VideoUrlViewModel


@Composable
fun SetupNavGraph(
    context: Context,
    viewModelSms: SmsViewModel,
    viewModelDataStore: DataStoreViewModel,
    viewModelDataBase: FahliCheckDbViewModel,
    viewModelPlanner: PlannerViewModel,
    viewModelVideoUrl: VideoUrlViewModel
) {
    val startDestination = viewModelDataStore.getUserLoginState().let {
        if (it) Screens.Home.route else Screens.Login.route
    }

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var badge by remember { mutableStateOf(false) }
    val currentDestination = navBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
        viewModelPlanner.allMessage.collectLatest { item ->
            if (item.isEmpty()) {
                badge = true
            }
        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                if (currentDestination == Screens.Home.route ||
                    currentDestination == Screens.About.route ||
                    currentDestination == Screens.Account.route ||
                    currentDestination == Screens.Planner.route
                ) {
                    BottomNavigationBar(navController = navController, badge)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier
                    .padding(innerPadding)

            ) {
                composable(route = Screens.Home.route) {
                    Home(navController)
                }
                composable(
                    route = Screens.About.route
                ) {
                    About()
                }
                composable(
                    route = Screens.Account.route
                ) {
                    AccountScreen(navController, viewModelSms, viewModelDataStore)
                }
                composable(
                    Screens.FahliMainScreen.route
                ) {
                    FahliMainHelp()
                }
                composable(route = Screens.Login.route) { _ ->
                    LoginScreen(navController, viewModelSms, context)
                }
                composable(
                    Screens.OtpAuth.route + "/{phoneNumber}/{otp}",
                    arguments = listOf(navArgument("phoneNumber") { type = NavType.StringType },
                        navArgument("otp") { type = NavType.IntType })

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
                                        launchSingleTop = true
                                        popUpTo(Screens.OtpAuth.route) { inclusive = true }
                                    }
                                }
                            }
                        )
                    }
                }

                composable(Screens.FahliCheckBox.route) {
                    FahliCheckList(context, viewModelDataStore, viewModelDataBase)
                }
                composable(route = Screens.Planner.route,
                    deepLinks = listOf(
                        navDeepLink {
                            uriPattern = "app://${Screens.Planner.route}"
                        }
                    )
                ) {
                    Planner(
                        navController,
                        viewModelSms = viewModelSms,
                        viewModelPlanner = viewModelPlanner,
                        viewModelDataStore = viewModelDataStore,
                        context
                    )
                }
                composable(Screens.VideoShow.route) {
                    VideoShow(navController, viewModel = viewModelVideoUrl)
                }
                composable(Screens.Article.route) {
                    Article(navController, viewModelVideoUrl)
                }
                composable(Screens.SuperMix.route) {
                    SuperMixScreen()
                }
                composable(
                    route = Screens.ArticleTxtShow.route
                ) {
                    ArticleTxtShow(viewModelVideoUrl)
                }
                composable(
                    route = Screens.SuperMixCalculator.route
                ) {
                    SuperMixFormulaScreen()
                }
                composable(
                    route = Screens.ShowAparatScreen.route + "/{URL}",
                            arguments = listOf( navArgument("URL") { type = NavType.StringType })
                ) {navBackStackEntry ->
                    val url = navBackStackEntry.arguments?.getString("URL")
                    AparatView(navController = navController, videoUrl = url?:"")
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
            title = "تماس با ما",
            route = Screens.About.route,
            selectedIcon = Icons.Filled.Info,
            unSelectedIcon = Icons.Outlined.Info,
            hasNews = false
        ),

        BtnNavItem(
            title = "اقدامات",
            route = Screens.Planner.route,
            selectedIcon = Icons.Filled.DateRange,
            unSelectedIcon = Icons.Outlined.DateRange,
            hasNews = badge
        ),

        BtnNavItem(
            title = "خانه",
            route = Screens.Home.route,
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            hasNews = false
        ),

        BtnNavItem(
            title = "حساب کاربری",
            route = Screens.Account.route,
            selectedIcon = Icons.Filled.AccountCircle,
            unSelectedIcon = Icons.Outlined.AccountCircle,
            hasNews = false
        )
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp,
        modifier = Modifier.height(60.dp)
    ) {
        items.forEach { btnNavItem ->
            NavigationBarItem(
                selected = currentDestination == btnNavItem.route,
                onClick = {
                    navController.navigate(route = btnNavItem.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(
                        text = btnNavItem.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.sans_bold))
                    )
                },
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = if (currentDestination == btnNavItem.route) {
                            btnNavItem.selectedIcon
                        } else {
                            btnNavItem.unSelectedIcon
                        },
                        contentDescription = btnNavItem.title,
                        modifier = Modifier.size(30.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

