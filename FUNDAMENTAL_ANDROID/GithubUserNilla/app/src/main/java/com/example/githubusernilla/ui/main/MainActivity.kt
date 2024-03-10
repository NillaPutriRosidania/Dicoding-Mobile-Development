package com.example.githubusernilla.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernilla.adapter.UserAdapter
import com.example.githubusernilla.databinding.ActivityMainBinding
import android.content.Intent
import com.example.githubusernilla.data.ItemsItem


class MainActivity : AppCompatActivity(), UserAdapter.OnUserClickListener {

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
