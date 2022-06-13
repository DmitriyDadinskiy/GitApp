package com.dd.gitapp.domain


import retrofit2.Call
import retrofit2.http.GET

interface GivUsersGitHabRepoApi {
    @GET("users")
    fun loadReposUsersList(): Call<List<UsersListGitHab>>
}