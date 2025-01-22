package salimi.mohamad.aragenejetpack.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import salimi.mohamad.aragenejetpack.data.remote.ApiInterfaceUrl
import salimi.mohamad.aragenejetpack.utils.Constants.URL_API
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UrlApiModule {

    @Singleton
    @Provides
    @Named("urlHttp")
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    @Named("UrlApi")
    fun provideRetrofitApi(@Named("urlHttp")okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Named("UrlApiInter")
    fun provideBaseApiInterfaceUrl(@Named("UrlApi") retrofit: Retrofit): ApiInterfaceUrl =
        retrofit.create(ApiInterfaceUrl::class.java)
}