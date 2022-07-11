package com.dd.gitapp.data


import com.dd.gitapp.domain.GivUsersListGitHabRepo
import com.dd.gitapp.domain.PersonalUserEntity
import com.dd.gitapp.domain.UsersListEntity
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.github.com/"

class RetrofitGivUsersListImpl : GivUsersListGitHabRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private var api: GivUsersGitHabRepoApi = retrofit.create(GivUsersGitHabRepoApi::class.java)

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
