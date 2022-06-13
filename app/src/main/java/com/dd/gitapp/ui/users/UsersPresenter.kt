package com.dd.gitapp.ui.users

import com.dd.gitapp.domain.GivUsersListGitHabRepo
import com.dd.gitapp.domain.UsersListGitHab

class UsersPresenter(
    private val usersListGitHabRepo: GivUsersListGitHabRepo,
) : UsersContract.Presenter {
    private var view: UsersContract.View? = null

    private var usersList: List<UsersListGitHab>? = null
    private var inProgress: Boolean = true

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        usersList?.let { view.showListUsers(it) }
    }

    override fun detach() {
        view = null

    }

    override fun onRefresh() {
        loadUserGidHab()
    }

    private fun loadUserGidHab() {
        view?.showProgress(false)
        inProgress = false
        usersListGitHabRepo.getUsersList(
            onSuccess = {
                view?.showProgress(true)
                usersList = it
                view?.showListUsers(it)
                inProgress = true
            },
            onError = {
                view?.showProgress(true)
                view?.showLoadingError(it)
                inProgress = true
            }
        )
    }
}