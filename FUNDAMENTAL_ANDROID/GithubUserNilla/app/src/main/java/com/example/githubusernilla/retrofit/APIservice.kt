package com.example.githubusernilla.retrofit
import com.example.githubusernilla.data.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIservice {
    @GET("search/users")
    fun searchUsers(@Query("q") username: String): Call<GithubResponse>
}
