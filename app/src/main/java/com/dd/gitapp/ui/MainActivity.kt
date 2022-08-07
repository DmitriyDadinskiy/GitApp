package com.dd.gitapp.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.gitapp.app
import com.dd.gitapp.data.UsersListEntity
import com.dd.gitapp.databinding.ActivityMainBinding
import com.dd.gitapp.ui.profile.USER_LOGIN
import com.dd.gitapp.ui.profile.UserCardActivity
import com.dd.gitapp.ui.users.UsersListAdapter
import com.dd.gitapp.ui.users.UsersViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable


class MainActivity : AppCompatActivity(), UsersListAdapter.ClickOnItemView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUsersList: UsersListAdapter
    private val viewModel: UsersViewModel by lazy { UsersViewModel(app.appComponent.givUsersRepo()) }

    private var viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        showProgress(true)

        initViewModel()
    }

    private fun initViewModel() {
        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe { showProgress(it) },
            viewModel.usersLiveData.subscribe { showListUsers(it) },
            viewModel.errorLiveData.subscribe { showLoadingError(it) }
        )
    }


    private fun init() {
        initRecyclerViewUsers()
        loadUserGidHab()
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
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
