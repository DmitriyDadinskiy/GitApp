package com.dd.gitapp.data

import com.dd.gitapp.domain.UsersListGitHab
import retrofit2.Call
import retrofit2.http.GET

interface GivUsersGitHabRepoApi {
    @GET("users")
    fun loadReposUsersList(): Call<List<UsersListGitHab>>
}