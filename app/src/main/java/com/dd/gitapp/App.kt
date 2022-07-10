package com.dd.gitapp

import android.app.Application
import android.content.Context
import com.dd.gitapp.data.GivUsersGitHabRepoApi
import com.dd.gitapp.data.RetrofitGivUsersListImpl
import com.dd.gitapp.domain.GivUsersListGitHabRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val baseUrl = "https://api.github.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private val api: GivUsersGitHabRepoApi by lazy { retrofit.create(GivUsersGitHabRepoApi::class.java) }

    val givUsersListGitHabRepo: GivUsersListGitHabRepo by lazy { RetrofitGivUsersListImpl(api) }
}

val Context.app get() = applicationContext as App
