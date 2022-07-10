package com.dd.gitapp.data


import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GivUsersGitHabRepoApi {
    @GET("users")
    fun loadReposUsersList(): Single<List<UsersListEntity>>

    @GET("users/{name}")
    fun loadReposPersonalUser(@Path("name") name: String): Call<PersonalUserEntity>

}