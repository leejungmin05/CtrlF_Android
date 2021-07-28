package com.thinlineit.ctrlf.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityCompleteRegisterBinding

class CompleteRegisterActivity : AppCompatActivity() {
    private val binding: ActivityCompleteRegisterBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_complete_register)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        binding.loginBtn.setOnClickListener {
            LoginActivity.start(this)
            finish()
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CompleteRegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

}