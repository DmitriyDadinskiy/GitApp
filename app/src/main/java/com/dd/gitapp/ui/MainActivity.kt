package com.dd.gitapp.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.gitapp.app
import com.dd.gitapp.domain.UsersListGitHab
import com.dd.gitapp.databinding.ActivityMainBinding
import com.dd.gitapp.domain.GivUsersListGitHabRepo
import com.dd.gitapp.ui.users.UsersListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUsersList: UsersListAdapter
    private val givUsersListGitHabRepo: GivUsersListGitHabRepo by lazy { app.givUsersListGitHabRepo }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgress(false)
        init()
    }

    private fun init() {
        initViewUsersList()
    }

    private fun initViewUsersList() {
        binding.apply {
            mainActivityListUsersRecyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.VERTICAL, false
            )
            adapterUsersList = UsersListAdapter(listOf())
            mainActivityListUsersRecyclerView.adapter = adapterUsersList
            givUsersListGitHabRepo.getUsersList(
                onSuccess = ::getListUsers,
                onError = ::loadingError
            )
        }
    }

    private fun getListUsers(result: List<UsersListGitHab>) {
        adapterUsersList.addUsers(result)
        showProgress(true)
    }

    private fun loadingError(throwable: Throwable) {
        Toast.makeText(this@MainActivity, "$throwable", Toast.LENGTH_LONG).show()
        Log.d(TAG, "ОШИБКА: $throwable")
        showProgress(false)

    }

    private fun showProgress(inProgress: Boolean) {
        binding.mainLoadUsersProgressBar.isVisible = inProgress
        binding.mainLoadUsersProgressBar.isVisible = !inProgress
    }
}