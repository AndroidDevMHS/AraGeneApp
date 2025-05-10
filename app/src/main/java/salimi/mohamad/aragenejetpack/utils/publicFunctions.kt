@file:Suppress("DEPRECATION")

package salimi.mohamad.aragenejetpack.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.viewModel.SmsViewModel
import java.security.MessageDigest
import kotlin.random.Random
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun getAppHash(context: Context): String {
    val packageName = context.packageName
    val signature = context.packageManager.getPackageInfo(packageName, 64).signatures?.get(0)
        ?.toByteArray()
    val messageDigest = MessageDigest.getInstance("SHA-256")
    if (signature != null) {
        messageDigest.update(signature)
    }
    val hashBytes = messageDigest.digest()
    val base64Hash = Base64.getEncoder().encodeToString(hashBytes).substring(0, 11)
    return base64Hash
}

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
