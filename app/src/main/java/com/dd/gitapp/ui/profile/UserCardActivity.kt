package com.dd.gitapp.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dd.gitapp.databinding.ActivityUserCardBinding

const val USER_LOGIN = "login"

class UserCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}