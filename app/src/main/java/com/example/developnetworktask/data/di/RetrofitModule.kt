package com.example.developnetworktask.data.di


import android.provider.SyncStateContract
import com.example.developnetworktask.data.DataStoreManger
import com.example.developnetworktask.data.source.remote.ApiBase
import com.example.developnetworktask.data.source.remote.ApiInterface
import com.example.developnetworktask.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())
             //  .addHeader(Constants.Token_HEADER ,DataStoreManger()?.read(Constants.Token_KEY))

            val request = requestBuilder.build()
            chain.proceed(request)

        }.build()

    @Provides
    @Singleton
    fun provideClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiBase.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
}