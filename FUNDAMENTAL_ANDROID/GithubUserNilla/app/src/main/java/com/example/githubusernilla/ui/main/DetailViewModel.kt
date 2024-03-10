package com.example.githubusernilla.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.retrofit.APIconfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val apiService = APIconfig.create() // Menggunakan fungsi create() dari APIconfig
    private val _userDetail = MutableLiveData<DetailUserResponse?>()
    val userDetail: LiveData<DetailUserResponse?> = _userDetail
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchUserDetail(username: String) {
        apiService.getDetailUser(username).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(call: Call<DetailUserResponse>, response: Response<DetailUserResponse>) {
                if (response.isSuccessful) {
                    val detailUser = response.body()
                    _userDetail.postValue(detailUser)
                } else {
                    _errorMessage.postValue("Failed to load user details")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _errorMessage.postValue("Error: ${t.message}")
            }
        })
    }
}
