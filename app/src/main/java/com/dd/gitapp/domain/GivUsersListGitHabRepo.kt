package com.dd.gitapp.domain

import androidx.annotation.WorkerThread


interface GivUsersListGitHabRepo {
    @WorkerThread
    fun getUsersList(
        onSuccess: (result: List<UsersListGitHab>) -> Unit,
        onError: (Throwable) -> Unit,
    )
}