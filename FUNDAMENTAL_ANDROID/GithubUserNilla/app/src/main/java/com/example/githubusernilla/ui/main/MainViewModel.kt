package com.example.githubusernilla.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var searchQuery: String? = null

    // Function to set search query
    fun setSearchQuery(query: String) {
        searchQuery = query
    }

    // Function to get search query
    fun getSearchQuery(): String? {
        return searchQuery
    }

    fun fetchData(username: String) {
        val query = getSearchQuery() // Get search query
        val APIservice = APIconfig.create()
        val call = APIservice.searchUsers(query ?: username)

        call.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(call: Call<GithubResponse>, response: Response<GithubResponse>) {
                if (response.isSuccessful) {
                    val userList = response.body()?.items ?: emptyList()
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