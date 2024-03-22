package com.example.githubusernilla.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubusernilla.ui.main.FollowFragment


class SectionsPagerAdapter(
    activity: AppCompatActivity,
    private val fragmentList: List<Fragment>,
    private val username: String
) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        val fragment = fragmentList[position]
        if (fragment is FollowFragment) {
            fragment.arguments = Bundle().apply {
                putInt(FollowFragment.ARG_POSITION, position + 1)
                putString(FollowFragment.ARG_USERNAME, username)
            }
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }
}

