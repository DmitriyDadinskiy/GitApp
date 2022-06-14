package com.dd.gitapp.domain




interface GivUsersListGitHabRepo {

    fun getUsersList(
        onSuccess: (result: List<UsersListEntity>) -> Unit,
        onError: (Throwable) -> Unit,
    )
}