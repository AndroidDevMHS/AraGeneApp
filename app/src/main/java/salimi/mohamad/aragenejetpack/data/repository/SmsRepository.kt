package salimi.mohamad.aragenejetpack.data.repository

import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.data.remote.ApiInterface
import javax.inject.Inject

class SmsRepository @Inject constructor(
    private val api: ApiInterface
) {
    suspend fun sendSms(smsRequest: SmsRequest) = api.sendSms(
        username = smsRequest.userName,
        password = smsRequest.password,
        from = smsRequest.fromNumber,
        to = smsRequest.toNumbers,
        text = smsRequest.messageContent
    )
}