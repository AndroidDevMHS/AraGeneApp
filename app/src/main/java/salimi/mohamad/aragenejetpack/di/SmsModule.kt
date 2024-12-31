package salimi.mohamad.aragenejetpack.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import salimi.mohamad.aragenejetpack.data.remote.ApiInterface
import salimi.mohamad.aragenejetpack.data.repository.SmsRepository
import salimi.mohamad.aragenejetpack.utils.Constants.BASE_URL
import salimi.mohamad.aragenejetpack.utils.Constants.BASE_URL_VIDEO
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    @Named("BaseRetrofit")
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Named("VideoRetrofit")
    fun provideRetrofitVideo(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL_VIDEO)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Named("BaseApi")
    fun provideBaseApiInterface(@Named("BaseRetrofit") retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Singleton
    @Provides
    @Named("ReposBaseApi")
    fun provideRepositorySms(
        @Named("BaseApi") smsApi: ApiInterface,
        @Named("VideoApi") videoApi: ApiInterface
    ): SmsRepository =SmsRepository(smsApi,videoApi)


        @Singleton
        @Provides
        @Named("VideoApi")
        fun provideVideoApiInterface(@Named("VideoRetrofit") retrofit: Retrofit): ApiInterface =
            retrofit.create(ApiInterface::class.java)
}
