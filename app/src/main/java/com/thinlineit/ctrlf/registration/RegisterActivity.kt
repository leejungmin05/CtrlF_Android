package com.thinlineit.ctrlf.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.registerBtn.setOnClickListener {
            if (viewModel.chkPassword()) {
                viewModel.requestSignUp()
                CompleteRegisterActivity.start(this)
                finish()
            } else Toast.makeText(this, ALERTPASSWORD, Toast.LENGTH_SHORT).show()
        }

        binding.regName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // do nothing
            } else viewModel.duplicateNickname()
        }

        viewModel.alertLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val ALERTPASSWORD = "비밀번호가 일치하지 않습니다."

}