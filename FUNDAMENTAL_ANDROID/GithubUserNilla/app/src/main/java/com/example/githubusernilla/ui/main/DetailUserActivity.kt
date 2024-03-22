package com.example.githubusernilla.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubusernilla.data.DetailUserResponse
import com.example.githubusernilla.databinding.ActivityDetailUserBinding
import androidx.lifecycle.ViewModelProvider
import com.example.githubusernilla.R
import com.example.githubusernilla.adapter.SectionsPagerAdapter
import com.example.githubusernilla.database.AppDatabase
import com.example.githubusernilla.database.FavoriteUserDao
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var factory: DetailViewModelFactory
    private lateinit var favoriteUserDao: FavoriteUserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteUserDao = AppDatabase.getInstance(applicationContext).favoriteUserDao()

        factory = DetailViewModelFactory(favoriteUserDao)

        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        val username = intent.getStringExtra("USERNAME") ?: ""

        viewModel.userDetail.observe(this) { user ->
            if (user != null) {
                updateUI(user)
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.progressBar.visibility = View.VISIBLE
        viewModel.fetchUserDetail(username)

        val viewPager2 = binding.viewPager2
        val tabLayout = binding.tabLayout

        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, listOf(FollowFragment(), FollowFragment()), username)

        viewPager2.adapter = sectionsPagerAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Follower"
                1 -> tab.text = "Following"
            }
        }.attach()

        binding.toggleFavorite.setOnCheckedChangeListener { _, isChecked ->
            val user = viewModel.userDetail.value
            val username = user?.login ?: ""
            if (isChecked) {
                user?.let { viewModel.addToFavorites(it) }
                binding.toggleFavorite.setBackgroundResource(R.drawable.favorite_toggle)
            } else {
                viewModel.removeFromFavorites(username)
                binding.toggleFavorite.setBackgroundResource(R.drawable.non_favorite_toggle)
            }
        }

        viewModel.isFavorite.observe(this) { isFavorite ->
            if (isFavorite) {
                binding.toggleFavorite.isChecked = true
                binding.toggleFavorite.setBackgroundResource(R.drawable.favorite_toggle)
            } else {
                binding.toggleFavorite.isChecked = false
                binding.toggleFavorite.setBackgroundResource(R.drawable.non_favorite_toggle)
            }
        }
    }

    private fun updateUI(user: DetailUserResponse) {
        Glide.with(this).load(user.avatarUrl).centerCrop().into(binding.imageView1)

        binding.usernameTextView.text = user.login
        binding.nameTextView.text = user.name
        binding.followersTextView.text = "Followers: ${user.followers}"
        binding.followingTextView.text = "Following: ${user.following}"
    }
}
