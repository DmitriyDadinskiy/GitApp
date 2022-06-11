package com.dd.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dd.gitapp.R
import com.dd.gitapp.domain.UsersListGitHab

class UsersListAdapter(
    private var usersListAdapter: List<UsersListGitHab>,
) : RecyclerView.Adapter<UsersListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListHolder {
        val view = LayoutInflater.from((parent.context))
            .inflate(R.layout.item_users_list, parent, false)
        return UsersListHolder(view)
    }

    override fun onBindViewHolder(holder: UsersListHolder, position: Int) {
        holder.bind(usersListAdapter[position])
    }

    override fun getItemCount() = usersListAdapter.size

    fun addUsers(usersListAdapter: List<UsersListGitHab>) {
        this.usersListAdapter = usersListAdapter
        notifyItemChanged(-1)
    }
}