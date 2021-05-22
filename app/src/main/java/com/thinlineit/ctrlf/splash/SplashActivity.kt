package com.thinlineit.ctrlf.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.thinlineit.ctrlf.MainActivity
import com.thinlineit.ctrlf.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Glide
            .with(this@SplashActivity)
            .asGif()
            .load(R.drawable.splash_gif)
            .into(iv_gif_view)

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
                // start loginActivity
            }
        }
    }

    private fun checkLogin(): Boolean {
        //TODO: check if user have login via sharedPreference
        return true
    }
}
