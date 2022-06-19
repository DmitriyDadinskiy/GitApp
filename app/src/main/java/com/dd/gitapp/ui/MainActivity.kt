package com.dd.gitapp.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.gitapp.app
import com.dd.gitapp.domain.UsersListEntity
import com.dd.gitapp.databinding.ActivityMainBinding
import com.dd.gitapp.ui.profile.USER_LOGIN
import com.dd.gitapp.ui.profile.UserCardActivity
import com.dd.gitapp.ui.users.*

class MainActivity : AppCompatActivity(), UsersListAdapter.ClickOnItemView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUsersList: UsersListAdapter
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        showProgress(true)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = extractViewModel()

        viewModel.progressLiveData.observe(this) { showProgress(it) }
        viewModel.usersLiveData.observe(this) { showListUsers(it) }
        viewModel.errorLiveData.observe(this) { showLoadingError(it) }
    }

    private fun extractViewModel(): UsersViewModel {
        return lastCustomNonConfigurationInstance as? UsersViewModel
            ?: UsersViewModel(app.givUsersListGitHabRepo)
    }


    override fun onRetainCustomNonConfigurationInstance(): UsersViewModel {
        return viewModel
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
            adapterUsersList = UsersListAdapter(listOf(), this@MainActivity)
            mainActivityListUsersRecyclerView.adapter = adapterUsersList
        }

    }

    override fun onClickItemView(data: UsersListEntity) {
        Toast.makeText(this@MainActivity, " ${data.login} ", Toast.LENGTH_LONG).show()
        startActivity(Intent(this@MainActivity, UserCardActivity::class.java)
            .apply {
                putExtra(USER_LOGIN, data.login)

            }
        )
    }


    private fun loadUserGidHab() {
        binding.mainLoadUsersButton.setOnClickListener {
            viewModel.loadUserGidHab()
        }

    }

    private fun showListUsers(result: List<UsersListEntity>) {
        adapterUsersList.addUsers(result)
        showProgress(true)
    }

    private fun showLoadingError(throwable: Throwable) {
        Toast.makeText(this@MainActivity, "$throwable", Toast.LENGTH_LONG).show()
        Log.d(TAG, "ОШИБКА: $throwable")
        showProgress(false)
    }

    private fun showProgress(inProgress: Boolean) {
        binding.mainLoadUsersProgressBar.isVisible = inProgress
        binding.mainLoadUsersProgressBar.isVisible = !inProgress
    }
}
