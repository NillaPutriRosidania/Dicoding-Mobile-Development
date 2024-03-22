package com.example.githubusernilla.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubusernilla.database.FavoriteUserDao

class DetailViewModelFactory(private val favoriteUserDao: FavoriteUserDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(favoriteUserDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

