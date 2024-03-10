package com.example.githubusernilla.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.databinding.ActivityDetailUserBinding
import android.widget.TextView
import androidx.activity.viewModels


class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME") ?: ""


        viewModel.userDetail.observe(this) { user ->
            updateUI(user ?: return@observe)
        }

        viewModel.fetchUserDetail(username)
    }

    private fun updateUI(user: DetailUserResponse) {
        Glide.with(this)
            .load(user.avatarUrl)
            .centerCrop()
            .into(binding.imageView1)

        binding.usernameTextView.text = user.login
        binding.nameTextView.text = user.name
        binding.followersTextView.text = "Followers: ${user.followers}"
        binding.followingTextView.text = "Following: ${user.following}"
    }
}
