package com.dd.gitapp.data


import com.google.gson.annotations.SerializedName

data class PersonalUserEntity(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("created_at")
    val createdAt: String,//да создания аккаунта
    @SerializedName("location")
    val location: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String
)