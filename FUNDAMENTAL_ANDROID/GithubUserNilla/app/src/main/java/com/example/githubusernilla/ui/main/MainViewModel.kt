package com.example.githubusernilla.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.data.GithubResponse
import com.example.githubusernilla.data.ItemsItem
import com.example.githubusernilla.retrofit.APIconfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _userList = MutableLiveData<List<ItemsItem>>()
    val userList: LiveData<List<ItemsItem>> = _userList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _userDetail = MutableLiveData<DetailUserResponse>()
    val userDetail: LiveData<DetailUserResponse> = _userDetail

    private var searchQuery: String? = null

    fun setSearchQuery(query: String) {
        searchQuery = query
    }

    fun getSearchQuery(): String? {
        return searchQuery
    }

    fun fetchUserDetail(username: String) {
        UserRepository.getUserDetail(username) { userDetail ->
            _userDetail.postValue(userDetail)
        }
    }

    fun fetchData(username: String) {
        val query = getSearchQuery()
        val APIservice = APIconfig.create()
        val call = APIservice.searchUsers(query ?: username)

        call.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                if (response.isSuccessful) {
                    val userList = response.body()?.items ?: emptyList()
                    Log.d("API Response", "User List: $userList")
                    _userList.postValue(userList as List<ItemsItem>?)
                } else {
                    _errorMessage.postValue("Failed to load data")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _errorMessage.postValue("Error: ${t.message}")
            }
        })
    }
}