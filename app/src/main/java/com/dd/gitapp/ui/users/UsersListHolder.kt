package com.dd.gitapp.ui.users

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dd.gitapp.domain.UsersListGitHab
import com.dd.gitapp.databinding.ItemUsersListBinding

class UsersListHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val binding = ItemUsersListBinding.bind(item)
    fun bind(usersListGitHab: UsersListGitHab) = with(binding) {
        binding.itemUserPhotoImageView.load(usersListGitHab.avatarUrl)
        binding.itemLoginTextView.text = usersListGitHab.login
        binding.itemIdTextView.text = usersListGitHab.id.toString()

    }
}