package com.dd.gitapp.domain


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query

interface GivUsersGitHabRepoApi {
    @GET("users")
    fun loadReposUsersList(): Call<List<UsersListEntity>>

    @GET("users/{name}")
    fun loadReposPersonalUser(@Path("name") name: String): Call<PersonalUserEntity>

}