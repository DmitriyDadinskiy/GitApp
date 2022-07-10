package com.dd.gitapp.data


import com.google.gson.annotations.SerializedName

data class UsersListEntity(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,

    )