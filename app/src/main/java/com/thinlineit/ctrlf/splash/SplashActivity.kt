package com.thinlineit.ctrlf.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.thinlineit.ctrlf.MainActivity
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.registration.LoginActivity
import com.thinlineit.ctrlf.repository.UserRepository
import com.thinlineit.ctrlf.util.Application
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val userRepository = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashView()
        startFirstActivity()
    }

    private fun loadSplashView() {
        Glide
            .with(this@SplashActivity)
            .asGif()
            .load(R.drawable.splash_gif)
            .into(splashView)
    }

    private fun startFirstActivity() {
        CoroutineScope(Dispatchers.Default).launch {
            delay(1500L)
            if (userRepository.mayLogin()) {
                MainActivity.start(this@SplashActivity)
            } else {
                LoginActivity.start(this@SplashActivity)
            }
            finish()
        }
    }
}
