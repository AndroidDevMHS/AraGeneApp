package salimi.mohamad.aragenejetpack.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SmsBroadcastReceiver : BroadcastReceiver() {

    private var listener: Listener? = null

    fun initListener(listener: Listener) {
        this.listener = listener
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == SmsRetriever.SMS_RETRIEVED_ACTION) {
            val extras = intent.extras
            val smsRetrieverStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
            when (smsRetrieverStatus.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val sms = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE)
                    val otp = parseOtp(sms)
                    listener?.onOtpReceived(otp)
                }

                else -> {}
            }
        }
    }

    private fun parseOtp(sms: String?): String? {
        val otpPattern = "\\d{4,6}".toRegex() // پیدا کردن کد OTP
        return otpPattern.find(sms.orEmpty())?.value
    }

    interface Listener {
        fun onOtpReceived(otp: String?)
    }
}