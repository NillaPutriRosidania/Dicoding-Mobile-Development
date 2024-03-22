package com.example.githubusernilla.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernilla.adapter.UserAdapter
import com.example.githubusernilla.data.ItemsItem
import com.example.githubusernilla.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity(), UserAdapter.OnUserClickListener {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this) // Inisialisasi layoutManager setelah binding

        val repository = FavoriteRepository(applicationContext)
        val viewModelFactory = FavoriteViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(FavoriteViewModel::class.java)

        adapter = UserAdapter(this)
        binding.recyclerView.adapter = adapter

        viewModel.getFavoriteUsers().observe(this) { users ->
            val items = arrayListOf<ItemsItem>()
            users.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl)
                items.add(item)
            }
            adapter.submitList(items)
        }
    }

    override fun onUserClick(user: ItemsItem) {
        val intent = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
        intent.putExtra("USERNAME", user.login)
        startActivity(intent)
    }
}
