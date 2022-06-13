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
import com.dd.gitapp.ui.users.UsersContract
import com.dd.gitapp.ui.users.UsersListAdapter
import com.dd.gitapp.ui.users.UsersPresenter

class MainActivity : AppCompatActivity(), UsersContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUsersList: UsersListAdapter
    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = UsersPresenter(app.givUsersListGitHabRepo)
        presenter.attach(this)
        init()
        showProgress(true)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun init() {
        initRecyclerViewUsers()
        loadUserGidHab()
    }

    private fun initRecyclerViewUsers() {
        binding.apply {
            mainActivityListUsersRecyclerView.layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.VERTICAL, false
            )
            adapterUsersList = UsersListAdapter(listOf())
            mainActivityListUsersRecyclerView.adapter = adapterUsersList
        }
    }

    private fun loadUserGidHab() {
        binding.mainLoadUsersButton.setOnClickListener {
            presenter.onRefresh()

        }

    }

    override fun showListUsers(result: List<UsersListGitHab>) {
        adapterUsersList.addUsers(result)
        showProgress(true)
    }

    override fun showLoadingError(throwable: Throwable) {
        Toast.makeText(this@MainActivity, "$throwable", Toast.LENGTH_LONG).show()
        Log.d(TAG, "ОШИБКА: $throwable")
        showProgress(false)

    }

    override fun showProgress(inProgress: Boolean) {
        binding.mainLoadUsersProgressBar.isVisible = inProgress
        binding.mainLoadUsersProgressBar.isVisible = !inProgress
    }
}