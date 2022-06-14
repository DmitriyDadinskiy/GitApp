package com.dd.gitapp.ui.users

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dd.gitapp.databinding.ItemUsersListBinding
import com.dd.gitapp.domain.UsersListEntity

class UsersListHolder(
    item: View,

    ) : RecyclerView.ViewHolder(item) {
    private val binding = ItemUsersListBinding.bind(item)

    fun bind(usersListEntity: UsersListEntity) = with(binding) {
        itemUserPhotoImageView.load(usersListEntity.avatarUrl)
        itemLoginTextView.text = usersListEntity.login
        itemIdTextView.text = usersListEntity.id.toString()

    }

}


