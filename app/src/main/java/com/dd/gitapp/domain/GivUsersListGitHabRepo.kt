package com.dd.gitapp.domain

import com.dd.gitapp.data.PersonalUserEntity
import com.dd.gitapp.data.UsersListEntity
import io.reactivex.rxjava3.core.Single


interface GivUsersListGitHabRepo {

    fun getUsersList(
        onSuccess: (result: List<UsersListEntity>) -> Unit,
        onError: (Throwable) -> Unit,
    )

    fun getUsersList(): Single<List<UsersListEntity>>

    fun getUser(
        name: String,
        onSuccess: (result: PersonalUserEntity) -> Unit,
        onError: (Throwable) -> Unit,
    )
}