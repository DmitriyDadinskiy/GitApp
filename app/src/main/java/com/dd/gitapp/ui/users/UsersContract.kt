package com.dd.gitapp.ui.users

import com.dd.gitapp.domain.UsersListEntity

interface UsersContract {
    interface View {
        fun showProgress(inProgress: Boolean)
        fun showLoadingError(throwable: Throwable)
        fun showListUsers(result: List<UsersListEntity>)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }
}