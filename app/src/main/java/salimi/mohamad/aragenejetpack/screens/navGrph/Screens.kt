package salimi.mohamad.aragenejetpack.screens.navGrph

sealed class Screens(val route:String) {

    object Splash: Screens(route = "SplashScreen")
    object Login: Screens(route = "Login")
    object OtpAuth: Screens(route = "OtpAuthScreen")
    object FahliCheckBox: Screens(route="FahliChecked")
    object Home: Screens(route = "Home")
    object About: Screens(route = "About")
    object Account: Screens(route = "Account")
    object FahliMainScreen:Screens(route = "FahliMainScreen")
}