package com.dd.gitapp.ui.users

import com.dd.gitapp.domain.UsersListGitHab

interface UsersContract {
    interface View{
        fun showProgress(inProgress: Boolean)
        fun showLoadingError(throwable: Throwable)
        fun showListUsers(result: List<UsersListGitHab>)
    }
    interface Presenter{
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }
}