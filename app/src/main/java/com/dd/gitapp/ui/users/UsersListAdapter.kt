package com.dd.gitapp.ui.users
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dd.gitapp.R
import com.dd.gitapp.domain.UsersListEntity

class UsersListAdapter(
    private var usersListAdapter: List<UsersListEntity>,
    private val clickOnItemView: ClickOnItemView,
) : RecyclerView.Adapter<UsersListHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListHolder {
        val view = LayoutInflater.from((parent.context))
            .inflate(R.layout.item_users_list, parent, false)
        return UsersListHolder(view)

    }

    override fun onBindViewHolder(holder: UsersListHolder, position: Int) {
        holder.bind(usersListAdapter[position])
        val data = usersListAdapter[position]
        holder.itemView.setOnClickListener { clickOnItemView.onClickItemView(data) }
    }

    override fun getItemCount() = usersListAdapter.size

    fun addUsers(usersListAdapter: List<UsersListEntity>) {
        this.usersListAdapter = usersListAdapter
        notifyItemChanged(-1)
    }

    interface ClickOnItemView {
        fun onClickItemView(data: UsersListEntity)
    }

}

