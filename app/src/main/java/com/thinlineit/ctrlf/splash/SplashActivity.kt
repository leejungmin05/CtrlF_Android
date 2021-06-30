package com.thinlineit.ctrlf.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.thinlineit.ctrlf.MainActivity
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.registration.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashView()
        startFirstActivity()
        finish()
    }

    private fun checkLogin(): Boolean {
        //TODO: check if user have login via sharedPreference
        return true
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
            if (checkLogin()) {
                this@SplashActivity.startActivity(
                    Intent(
                        this@SplashActivity,
                        MainActivity::class.java
                    )
                )
            } else {
                this@SplashActivity.startActivity(
                    Intent(
                        this@SplashActivity,
                        LoginActivity::class.java
                    )
                )
            }
        }
    }
}
