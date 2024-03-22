package com.example.githubusernilla.retrofit

import com.example.githubusernilla.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIconfig {
    private const val BASE_URL = BuildConfig.BASE_URL

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(AuthorizationInterceptor()).build()
    }

    fun create(): APIservice {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(APIservice::class.java)
    }
}
