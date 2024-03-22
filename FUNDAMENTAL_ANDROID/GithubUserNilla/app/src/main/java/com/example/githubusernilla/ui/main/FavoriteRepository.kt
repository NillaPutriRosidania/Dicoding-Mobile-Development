package com.example.githubusernilla.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.githubusernilla.database.AppDatabase
import com.example.githubusernilla.database.FavoriteUser
import com.example.githubusernilla.database.FavoriteUserDao

class FavoriteRepository(context: Context) {
    private val favoriteUserDao: FavoriteUserDao =
        AppDatabase.getInstance(context).favoriteUserDao()

    fun getFavoriteUsers(): LiveData<List<FavoriteUser>> {
        return favoriteUserDao.getFavoriteUsers()
    }
}
