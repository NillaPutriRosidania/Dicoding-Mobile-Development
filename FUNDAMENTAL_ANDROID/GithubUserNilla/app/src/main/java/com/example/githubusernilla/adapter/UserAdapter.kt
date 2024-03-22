package com.example.githubusernilla.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubusernilla.data.ItemsItem
import com.example.githubusernilla.databinding.ListUserBinding

class UserAdapter(
    private val onUserClickListener: OnUserClickListener
) : ListAdapter<ItemsItem, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    interface OnUserClickListener {
        fun onUserClick(user: ItemsItem)
    }

    inner class UserViewHolder(private val binding: ListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val user = getItem(position)
                    onUserClickListener.onUserClick(user)
                }
            }
        }

        fun bind(user: ItemsItem) {
            binding.apply {
                Glide.with(root)
                    .load(user.avatarUrl)
                    .apply(RequestOptions().override(100, 100))
                    .into(avatarImageView)
                usernameTextView.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserDiffCallback : DiffUtil.ItemCallback<ItemsItem>() {
        override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
            return oldItem == newItem
        }
    }
}
