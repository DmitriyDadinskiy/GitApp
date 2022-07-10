package com.dd.gitapp.ui.users


import androidx.lifecycle.ViewModel
import com.dd.gitapp.domain.GivUsersListGitHabRepo
import com.dd.gitapp.data.UsersListEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class UsersViewModel(
    private val usersListGitHabRepo: GivUsersListGitHabRepo,
) : ViewModel() {
    val usersLiveData: Observable<List<UsersListEntity>> = BehaviorSubject.create()
    val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    fun loadUserGidHab() {
        progressLiveData.mutable().onNext(false)
        usersListGitHabRepo.getUsersList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    progressLiveData.mutable().onNext(true)
                    usersLiveData.mutable().onNext(it)
                },
                onError = {
                    progressLiveData.mutable().onNext(true)
                    errorLiveData.mutable().onNext(it)

                }
            )

    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not Subject")
    }
}