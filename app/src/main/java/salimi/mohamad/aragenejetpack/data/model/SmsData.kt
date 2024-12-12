package salimi.mohamad.aragenejetpack.data.model

data class SmsRequest(
    val userName: String,
    val password: String,
    val fromNumber: String,
    val toNumbers: String,
    val messageContent: String,
    val isFlash: Boolean = false,
    val sendDelay: Int = 0
)

data class GetCreditResponse(
    val getCreditResult: String
)

data class SendSmsResponse(
    val result: String
)