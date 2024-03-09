package com.example.githubusernilla.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubusernilla.R
import com.example.githubusernilla.data.ItemsItem

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList: MutableList<ItemsItem> = mutableListOf()

    fun setData(newData: List<ItemsItem>) {
        userList.clear()
        userList.addAll(newData)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: ItemsItem) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .apply(RequestOptions().override(100, 100))
                    .into(findViewById(R.id.avatarImageView))
                findViewById<android.widget.TextView>(R.id.usernameTextView).text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }
}
