package com.example.githubusernilla.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.database.FavoriteUser
import com.example.githubusernilla.database.FavoriteUserDao
import com.example.githubusernilla.retrofit.APIconfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val favoriteUserDao: FavoriteUserDao) : ViewModel() {
    private val apiService = APIconfig.create()
    private val _userDetail = MutableLiveData<DetailUserResponse?>()
    val userDetail: LiveData<DetailUserResponse?> = _userDetail
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun fetchUserDetail(username: String) {
        apiService.getDetailUser(username).enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>, response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    val detailUser = response.body()
                    _userDetail.postValue(detailUser)
                    detailUser?.login?.let { username ->
                        checkIsFavorite(username)
                    }
                } else {
                    _errorMessage.postValue("Failed to load user details")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _errorMessage.postValue("Error: ${t.message}")
            }
        })
    }

    fun addToFavorites(user: DetailUserResponse) {
        val favoriteUser = FavoriteUser(user.login ?: "", user.avatarUrl ?: "")
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUserDao.addToFavorite(favoriteUser)
            setFavoriteStatus(true)
        }
    }


    fun removeFromFavorites(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUserDao.removeFromFavorite(username)
            setFavoriteStatus(false)
        }
    }


    private fun checkIsFavorite(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorited = favoriteUserDao.checkUser(username) > 0
            setFavoriteStatus(isFavorited)
            Log.d("FavoriteStatus", "Is favorite: $isFavorited")
        }
    }

    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser?> {
        return favoriteUserDao.getFavoriteUserByUsername(username)
    }


    private fun setFavoriteStatus(isFavorite: Boolean) {
        _isFavorite.postValue(isFavorite)
    }
}
