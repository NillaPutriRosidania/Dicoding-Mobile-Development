package com.example.githubusernilla.ui.main

import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.data.ItemsItem
import com.example.githubusernilla.retrofit.APIconfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserDetailRepository {
    private val APIservice = APIconfig.create()

    fun getUserDetail(username: String, callback: (DetailUserResponse?) -> Unit) {
        APIservice.getDetailUser(username).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>, response: Response<DetailUserResponse>
            ) {
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

    fun getFollowers(username: String, callback: (List<ItemsItem>?, String?) -> Unit) {
        APIservice.getFollowers(username).enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>, response: Response<List<ItemsItem>>
            ) {
                if (response.isSuccessful) {
                    val followers = response.body()
                    callback(followers, null)
                } else {
                    callback(null, "Failed to fetch followers")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                callback(null, "Error: ${t.message}")
            }
        })
    }

    fun getFollowing(username: String, callback: (List<ItemsItem>?, String?) -> Unit) {
        APIservice.getFollowing(username).enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>, response: Response<List<ItemsItem>>
            ) {
                if (response.isSuccessful) {
                    val following = response.body()
                    callback(following, null)
                } else {
                    callback(null, "Failed to fetch following")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                callback(null, "Error: ${t.message}")
            }
        })
    }
}
