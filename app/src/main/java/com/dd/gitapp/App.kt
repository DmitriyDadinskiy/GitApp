package com.dd.gitapp

import android.app.Application
import android.content.Context
import com.dd.gitapp.data.RetrofitGivUsersListImpl
import com.dd.gitapp.domain.GivUsersListGitHabRepo

class App : Application() {
    val givUsersListGitHabRepo: GivUsersListGitHabRepo by lazy { RetrofitGivUsersListImpl() }
}

val Context.app get() = applicationContext as App
