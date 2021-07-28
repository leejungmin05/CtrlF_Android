package com.thinlineit.ctrlf.registration

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.navigation.findNavController
import com.thinlineit.ctrlf.MainActivity
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_register)
    }
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }
    private val Success = 0
    private val Failure = 1


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.emailStatus.observe(this) {
            if (it == Failure) {
                binding.regEmail.background = getDrawable(R.drawable.border_edittext_error)
            } else if (it == Success) {
                binding.regEmail.background = getDrawable(R.drawable.border_edittext)
            }
        }

        viewModel.codeStatus.observe(this) {
            if (it == Failure) {
                binding.regCode.background = getDrawable(R.drawable.border_edittext_error)
            } else if (it == Success) {
                binding.regCode.background = getDrawable(R.drawable.border_edittext)
            }
        }

        viewModel.nicknameStatus.observe(this) {
            if (it == Failure) {
                binding.regName.background = getDrawable(R.drawable.border_edittext_error)
            } else if (it == Success) {
                binding.regName.background = getDrawable(R.drawable.border_edittext)
            }
        }

        viewModel.pwdStatus.observe(this) {
            if (it == Failure) {
                binding.regPassword.background = getDrawable(R.drawable.border_edittext_error)
                binding.regPassword2.background = getDrawable(R.drawable.border_edittext_error)
            } else if (it == Success) {
                binding.regPassword.background = getDrawable(R.drawable.border_edittext)
                binding.regPassword2.background = getDrawable(R.drawable.border_edittext)
            }
        }

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }


}