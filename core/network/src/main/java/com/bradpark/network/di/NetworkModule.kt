package com.bradpark.network.di

import com.bradpark.network.BuildConfig
import com.bradpark.network.retrofit.RetrofitNetworkApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBookSearchNetworkApi(
        retrofit: Retrofit
    ) = retrofit.create<RetrofitNetworkApi>()

    @Provides
    @Singleton
    fun provideRetrofitNetwork(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.KAKAO_URL)
        .addConverterFactory(GsonConverterFactory.create(gsonDate))
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder().addHeader(
                            "Authorization",
                            "KakaoAK ${BuildConfig.KAKAO_API}"
                        ).build()
                    )

                }
                .build()
        )
        .build()

    private val gsonDate: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        .create()

    private val networkJson: Json = Json {
        ignoreUnknownKeys = true
    }

}