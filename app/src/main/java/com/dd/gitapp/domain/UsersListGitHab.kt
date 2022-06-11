package com.dd.gitapp.domain


import com.google.gson.annotations.SerializedName

data class UsersListGitHab(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,

    )