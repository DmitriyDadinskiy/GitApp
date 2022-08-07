package com.dd.gitapp.di

import com.dd.gitapp.domain.GivUsersListGitHabRepo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModules::class])
interface AppComponent {
    fun givUsersRepo(): GivUsersListGitHabRepo
}