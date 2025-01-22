package salimi.mohamad.aragenejetpack.data.repository

import salimi.mohamad.aragenejetpack.data.model.SmsRequest
import salimi.mohamad.aragenejetpack.data.remote.ApiInterface
import javax.inject.Inject
import javax.inject.Named

class SmsRepository @Inject constructor(
    @Named("BaseApi") private val smsApi: ApiInterface,
) {
     fun sendSms(smsRequest: SmsRequest) = smsApi.sendSms(
        username = smsRequest.userName,
        password = smsRequest.password,
        from = smsRequest.fromNumber,
        to = smsRequest.toNumbers,
        text = smsRequest.messageContent
    )
}
