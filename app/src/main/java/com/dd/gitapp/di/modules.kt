package com.dd.gitapp.di

import com.dd.gitapp.data.GivUsersGitHabRepoApi
import com.dd.gitapp.data.RetrofitGivUsersListImpl
import com.dd.gitapp.domain.GivUsersListGitHabRepo
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    val baseUrl = "https://api.github.com/"

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single<GivUsersGitHabRepoApi> { get<Retrofit>().create(GivUsersGitHabRepoApi::class.java) }

    single<GivUsersListGitHabRepo> { RetrofitGivUsersListImpl(get()) }

}

