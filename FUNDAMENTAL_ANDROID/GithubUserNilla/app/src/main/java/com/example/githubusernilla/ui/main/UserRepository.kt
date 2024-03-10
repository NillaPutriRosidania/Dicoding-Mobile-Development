package com.example.githubusernilla.ui.main

import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.retrofit.APIconfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object UserRepository {
    private val APIservice = APIconfig.create()

    fun getUserDetail(username: String, callback: (DetailUserResponse?) -> Unit) {
        APIservice.getDetailUser(username).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(call: Call<DetailUserResponse>, response: Response<DetailUserResponse>) {
                if (response.isSuccessful) {
                    val userDetail = response.body()
                    callback(userDetail)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}
