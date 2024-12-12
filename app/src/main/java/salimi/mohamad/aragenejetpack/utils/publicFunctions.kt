@file:Suppress("DEPRECATION")

package salimi.mohamad.aragenejetpack.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel
import kotlin.random.Random

fun randomFourDigitNumber(): Int {
    return Random.nextInt(1000, 9999) // عدد تصادفی بین 1000 و 9999
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}


fun loginSms(phoneNumber:String,otpCode:Int,viewModel: SmsViewModel){
    viewModel.sendSms(
        SmsRequest(
            userName = "t.09199804478",
            password = "sbe$830",
            fromNumber = "10009611",
            toNumbers = phoneNumber,
            messageContent = " به آراژن ویرا خوش آمدید کد تایید شما $otpCode"
        )
    )
}
