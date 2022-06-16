package com.dd.gitapp.ui.profile

import com.dd.gitapp.domain.GivUsersListGitHabRepo


class UserCardPresenter(
    private val usersListGitHabRepo: GivUsersListGitHabRepo,
) : UserCardContract.Presenter {
    private var view: UserCardContract.View? = null
    private lateinit var login: String

    override fun attach(view: UserCardContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh(userName: String) {
        login = userName
        loadUserCardGidHab()
    }


    private fun loadUserCardGidHab() {
        usersListGitHabRepo.getUser(
            name = login,
            onSuccess = { view?.showUser(it) }
        ) { view?.showLoadingError(it) }
    }
}