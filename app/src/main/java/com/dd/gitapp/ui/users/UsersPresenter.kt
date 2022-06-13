package com.dd.gitapp.ui.users

import com.dd.gitapp.domain.GivUsersListGitHabRepo

class UsersPresenter(
    private val usersListGitHabRepo: GivUsersListGitHabRepo,
) : UsersContract.Presenter {
    private var view: UsersContract.View? = null
    override fun attach(view: UsersContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        loadUserGidHab()
    }

    private fun loadUserGidHab() {
        view?.showProgress(false)
        usersListGitHabRepo.getUsersList(
            onSuccess = {
                view?.showProgress(true)
                view?.showListUsers(it)
            },
            onError = {
                view?.showProgress(true)
                view?.showLoadingError(it)
            }
        )
    }
}