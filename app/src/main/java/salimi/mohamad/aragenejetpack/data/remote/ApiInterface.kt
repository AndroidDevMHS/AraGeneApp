package salimi.mohamad.aragenejetpack.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import salimi.mohamad.aragenejetpack.data.model.GetCreditResponse
import salimi.mohamad.aragenejetpack.data.model.SendSmsResponse
import salimi.mohamad.aragenejetpack.data.model.SmsRequest

interface ApiInterface {

    @GET("SendSms")
    fun sendSms(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("text") text: String
    ): Call<Int> // اگر نیاز به بررسی پاسخ دارید، نوع داده را به طور مناسب تغییر دهید.

    @POST("GetCredit")
    fun getCredit(@Body credentials: Map<String, String>): Call<GetCreditResponse>
}