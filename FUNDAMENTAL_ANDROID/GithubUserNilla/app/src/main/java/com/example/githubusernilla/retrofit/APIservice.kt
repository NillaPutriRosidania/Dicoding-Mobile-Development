package com.example.githubusernilla.retrofit

import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.data.GithubResponse
import com.example.githubusernilla.data.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIservice {
    @GET("search/users")
    fun searchUsers(@Query("q") username: String): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}


