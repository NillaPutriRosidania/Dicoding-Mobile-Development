package com.example.githubusernilla.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernilla.adapter.UserAdapter
import com.example.githubusernilla.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.githubusernilla.data.ItemsItem
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), UserAdapter.OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var settingPreferences: SettingPreferences
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settingPreferences = SettingPreferences.getInstance(applicationContext.dataStore)

        lifecycleScope.launch {
            settingPreferences.getThemeSetting().collect { isDarkModeActive ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        viewModelFactory = MainViewModelFactory(settingPreferences)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.userList.observe(this, { userList ->
            adapter.submitList(userList)
            binding.progressBar.visibility = View.GONE
        })

        binding.favoriteButton.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }

        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        adapter = UserAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.text = searchView.text
                searchView.hide()
                viewModel.setSearchQuery(searchView.text.toString())
                binding.progressBar.visibility = View.VISIBLE
                fetchData()
                false
            }
        }
        fetchData()
    }

    private fun fetchData() {
        viewModel.fetchData("Arif")
    }

    override fun onUserClick(user: ItemsItem) {
        val username = user.login ?: ""
        onButtonClick(username)
    }

    private fun onButtonClick(username: String) {
        val intent = Intent(this, DetailUserActivity::class.java)
        intent.putExtra("USERNAME", username)
        startActivity(intent)
    }
}
