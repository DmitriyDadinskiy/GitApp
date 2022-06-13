package com.dd.gitapp.data


import com.dd.gitapp.domain.GivUsersGitHabRepoApi
import com.dd.gitapp.domain.GivUsersListGitHabRepo
import com.dd.gitapp.domain.UsersListGitHab
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.github.com/"

class RetrofitGivUsersListImpl : GivUsersListGitHabRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: GivUsersGitHabRepoApi = retrofit.create(GivUsersGitHabRepoApi::class.java)

    override fun getUsersList(
        onSuccess: (result: List<UsersListGitHab>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {

        api.loadReposUsersList().enqueue(object : Callback<List<UsersListGitHab>> {
            override fun onResponse(
                call: Call<List<UsersListGitHab>>,
                response: Response<List<UsersListGitHab>>,
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError.invoke(Throwable())
                }
            }

            override fun onFailure(call: Call<List<UsersListGitHab>>, t: Throwable) {
                onError(t)
            }

        })
    }
}
