package com.example.githubusernilla.retrofit
import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.data.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface APIservice {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUsers(@Query("q") username: String): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>
}

