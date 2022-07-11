package com.dd.gitapp.ui.profile

import com.dd.gitapp.data.PersonalUserEntity


interface UserCardContract {
    interface View {
        fun showUser(result: PersonalUserEntity)
        fun showLoadingError(throwable: Throwable)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh(userName: String)
    }
}