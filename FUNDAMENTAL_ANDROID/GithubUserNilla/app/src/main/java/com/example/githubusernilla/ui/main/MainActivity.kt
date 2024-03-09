package com.example.githubusernilla.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernilla.adapter.UserAdapter
import com.example.githubusernilla.data.GithubResponse
import com.example.githubusernilla.data.ItemsItem
import com.example.githubusernilla.databinding.ActivityMainBinding
import com.example.githubusernilla.retrofit.APIconfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.userList.observe(this, { userList ->
            adapter.setData(userList)
            binding.progressBar.visibility = View.GONE
        })

        adapter = UserAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    viewModel.setSearchQuery(searchView.text.toString()) // Set search query in ViewModel
                    fetchData()
                    false
                }
        }
        fetchData()
    }

    private fun fetchData() {
        viewModel.fetchData("Arif") // Fetch data with default query
    }
}