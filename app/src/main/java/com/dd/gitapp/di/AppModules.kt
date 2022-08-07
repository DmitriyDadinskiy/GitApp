package com.dd.gitapp.di

import com.dd.gitapp.data.GivUsersGitHabRepoApi
import com.dd.gitapp.data.RetrofitGivUsersListImpl
import com.dd.gitapp.domain.GivUsersListGitHabRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModules {
   private val baseUrl = "https://api.github.com/"


    @Provides
    @Singleton
    fun provideUsersRepo(
        api: GivUsersGitHabRepoApi
    ): GivUsersListGitHabRepo {
        return RetrofitGivUsersListImpl(api)
    }

    @Provides
    @Singleton
    fun provideGitHabApi(
        retrofit: Retrofit
    ): GivUsersGitHabRepoApi {
        return retrofit.create(GivUsersGitHabRepoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}