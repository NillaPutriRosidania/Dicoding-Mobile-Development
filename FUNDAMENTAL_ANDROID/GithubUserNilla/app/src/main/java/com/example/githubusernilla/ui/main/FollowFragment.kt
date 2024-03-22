package com.example.githubusernilla.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusernilla.adapter.UserAdapter
import com.example.githubusernilla.databinding.FragmentFollowBinding
import com.example.githubusernilla.data.ItemsItem

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding
    private lateinit var adapter: UserAdapter
    private val viewModel: FollowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabPosition = arguments?.getInt(ARG_POSITION) ?: 1
        val username = arguments?.getString(ARG_USERNAME) ?: ""

        adapter = UserAdapter(object : UserAdapter.OnUserClickListener {
            override fun onUserClick(user: ItemsItem) {
            }
        })
        binding.rvFollowList.adapter = adapter
        binding.rvFollowList.layoutManager = LinearLayoutManager(requireContext())

        if (tabPosition == 1) {
            viewModel.fetchFollowers(username)
        } else {
            viewModel.fetchFollowing(username)
        }

        viewModel.followList.observe(viewLifecycleOwner) { userList ->
            adapter.submitList(userList)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }
}