package salimi.mohamad.aragenejetpack.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import salimi.mohamad.aragenejetpack.data.model.ArticleRes
import salimi.mohamad.aragenejetpack.data.model.VideoUrlResponseWrapper

interface ApiInterface {

    @GET("SendSms")
    fun sendSms(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("text") text: String
    ): Call<Int>

    @GET("41f95f01143ca4a4b46b6cc92b1d07b6/videouRl/sheet1")
    suspend fun videoUrl(): Response<VideoUrlResponseWrapper>

    @GET("41f95f01143ca4a4b46b6cc92b1d07b6/appUrl/sheet2")
    suspend fun getArticles(): ArticleRes
}
