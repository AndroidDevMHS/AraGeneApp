package salimi.mohamad.aragenejetpack.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import salimi.mohamad.aragenejetpack.data.remote.ApiInterface
import salimi.mohamad.aragenejetpack.utils.Constants.BASE_URL
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SmsModule {

    @Singleton
    @Provides
    fun provideOkHttpsClient():OkHttpClient=
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit=
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideApiInterface(retrofit:Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
}