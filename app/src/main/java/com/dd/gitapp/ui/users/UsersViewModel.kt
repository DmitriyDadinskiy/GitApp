package com.dd.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dd.gitapp.domain.GivUsersListGitHabRepo
import com.dd.gitapp.domain.UsersListEntity

class UsersViewModel(
    private val usersListGitHabRepo: GivUsersListGitHabRepo,
) : ViewModel() {
    val usersLiveData: LiveData<List<UsersListEntity>> = MutableLiveData()
    val errorLiveData: LiveData<Throwable> = MutableLiveData()
    val progressLiveData: LiveData<Boolean> = MutableLiveData()

     fun loadUserGidHab() {
        progressLiveData.mutable().postValue(false)
        usersListGitHabRepo.getUsersList(
            onSuccess = {
                progressLiveData.mutable().postValue(true)
                usersLiveData.mutable().postValue(it)
            },
            onError = {
                progressLiveData.mutable().postValue(true)
                errorLiveData.mutable().postValue(it)

            }
        )
    }
    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException("It is not MutableLiveData o_O")
    }
}