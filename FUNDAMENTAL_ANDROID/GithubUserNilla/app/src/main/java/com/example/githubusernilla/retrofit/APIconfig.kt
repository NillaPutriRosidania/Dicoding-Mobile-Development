package com.example.githubusernilla.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIconfig {
    private const val BASE_URL = "https://api.github.com/"

    fun create(): APIservice {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIservice::class.java)
    }
}