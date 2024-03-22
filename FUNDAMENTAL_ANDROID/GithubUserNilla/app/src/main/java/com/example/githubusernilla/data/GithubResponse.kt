package com.example.githubusernilla.data

import com.google.gson.annotations.SerializedName

class GithubResponse(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    val items: List<ItemsItem?>? = null
)

class ItemsItem(

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,
)
