package com.thinlineit.ctrlf.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityRegisterBinding
import com.thinlineit.ctrlf.util.ResourceProvider
import com.thinlineit.ctrlf.util.observeIfNotHandled
import com.thinlineit.ctrlf.util.setBackground

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_register)
    }
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java).apply {
            resourceProvider = ResourceProvider(this@RegisterActivity)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.emailStatus.observe(this) {
            if (it == FAILURE) {
                binding.regEmail.setBackground(R.drawable.border_edittext_error)
            } else if (it == SUCCESS) {
                binding.regEmail.setBackground(R.drawable.border_edittext)
            }
        }

        viewModel.codeStatus.observe(this) {
            if (it == FAILURE) {
                binding.regCode.setBackground(R.drawable.border_edittext_error)
            } else if (it == SUCCESS) {
                binding.regCode.setBackground(R.drawable.border_edittext)
            }
        }

        viewModel.nicknameStatus.observe(this) {
            if (it == FAILURE) {
                binding.regName.setBackground(R.drawable.border_edittext_error)
            } else if (it == SUCCESS) {
                binding.regName.setBackground(R.drawable.border_edittext)
            }
        }

        viewModel.pwdStatus.observe(this) {
            if (it == FAILURE) {
                binding.regPassword.setBackground(R.drawable.border_edittext_error)
                binding.regPassword2.setBackground(R.drawable.border_edittext_error)
            } else if (it == SUCCESS) {
                binding.regPassword.setBackground(R.drawable.border_edittext)
                binding.regPassword2.setBackground(R.drawable.border_edittext)
            }
        }

        viewModel.registerClick.observeIfNotHandled(this) {
            CompleteRegisterActivity.start(this)
            finish()
        }
    }

    companion object {
        private const val SUCCESS = 0
        private const val FAILURE = 1

        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }
}