package com.dd.gitapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.dd.gitapp.app
import com.dd.gitapp.databinding.ActivityUserCardBinding
import com.dd.gitapp.domain.PersonalUserEntity

const val USER_LOGIN = "userLogin"

class UserCardActivity : AppCompatActivity(), UserCardContract.View {
    private lateinit var binding: ActivityUserCardBinding
    private var userName = ""
    private lateinit var presenter: UserCardContract.Presenter
    lateinit var dropEnd: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        presenter = UserCardPresenter(app.givUsersListGitHabRepo)
        presenter.attach(this)

    }

    private fun init() {
        getUserName()

    }

    override fun onResume() {
        super.onResume()
        presenter.onRefresh(userName)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    private fun getUserName() {
        val putInfoUser = intent.extras
        if (putInfoUser != null) {
            userName = putInfoUser.getString(USER_LOGIN, "")
            binding.loginTextView.text = userName
        }
    }


    override fun showUser(result: PersonalUserEntity) {
        dropEnd = result.createdAt
        val resultString = dropEnd.dropLast(10)
        with(binding) {
            nameUserTextView.text = result.name
            userPhotoImageView.load(result.avatarUrl)
            dataRegistrationTextView.text = resultString
            cityTextView.text = result.location
            companyTextView.text = result.company
        }
    }

    override fun showLoadingError(throwable: Throwable) {
        Toast.makeText(this@UserCardActivity, "$throwable", Toast.LENGTH_LONG).show()
    }
}