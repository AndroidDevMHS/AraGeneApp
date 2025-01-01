package salimi.mohamad.aragenejetpack.screens.navGrph

sealed class Screens(val route:String) {

    data object Splash: Screens(route = "SplashScreen")
    data object Login: Screens(route = "Login")
    data  object OtpAuth: Screens(route = "OtpAuthScreen")
    data object FahliCheckBox: Screens(route="FahliChecked")
    data object Home: Screens(route = "Home")
    data object About: Screens(route = "About")
    data object Account: Screens(route = "Account")
    data object FahliMainScreen:Screens(route = "FahliMainScreen")
    data object Planner:Screens(route = "Planner")
    data object VideoShow:Screens(route = "videoShow")
    data object Article:Screens(route = "Article")
    data object SuperMix:Screens(route = "SuperMix")
    data object ArticleTxtShow:Screens(route = "ArticleTxtShow")
    data object SuperMixCalculator:Screens(route = "SuperMixCalculator")

}