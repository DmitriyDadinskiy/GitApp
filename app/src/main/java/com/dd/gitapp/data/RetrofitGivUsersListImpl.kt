package com.dd.gitapp.data


import com.dd.gitapp.domain.GivUsersListGitHabRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class RetrofitGivUsersListImpl(
    private var api: GivUsersGitHabRepoApi
) : GivUsersListGitHabRepo {

    override fun getUsersList(
        onSuccess: (result: List<UsersListEntity>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        api.loadReposUsersList().subscribeBy(
            onSuccess = { onSuccess.invoke(it) },
            onError = { onError.invoke(it) }
        )
    }

    override fun getUsersList(): Single<List<UsersListEntity>> = api.loadReposUsersList()


    override fun getUser(
        name: String,
        onSuccess: (result: PersonalUserEntity) -> Unit,
        onError: (Throwable) -> Unit,
    ) {

        api.loadReposPersonalUser(name).enqueue(object : Callback<PersonalUserEntity> {
            override fun onResponse(
                call: Call<PersonalUserEntity>,
                response: Response<PersonalUserEntity>,
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError.invoke(Throwable())
                }
            }

            override fun onFailure(call: Call<PersonalUserEntity>, t: Throwable) {
                onError(t)
            }

        })
    }
}
