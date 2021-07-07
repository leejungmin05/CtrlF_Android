package com.thinlineit.ctrlf.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.thinlineit.ctrlf.MainActivity
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.ActivityLoginBinding
import com.thinlineit.ctrlf.util.Application

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this

        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val pwd = binding.loginPwd.text.toString()
            Application.prefs.setString("email",email)
            Application.prefs.setString("pwd",pwd)
            loginCheck(email,pwd)

        }

        binding.registerBtn.setOnClickListener {
            setRegisterActivity()
        }
    }

    fun loginCheck(email: String, pwd: String) {
        if(email.equals("") || pwd.equals("")) {
            Toast.makeText(this,"이메일과 비밀번호를 입력해주세요",Toast.LENGTH_LONG).show()
        } else {
            doLogin(email,pwd)
        }
    }

    private fun doLogin(email: String, pwd: String) {
        if(email == "test" ) {
            if(pwd == "1234") {
                MainActivity.start(this)
                finish()
            } else {
                Toast.makeText(this,"이메일과 비밀번호를 확인해주세요",Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this,"회원가입해줘!!!!!",Toast.LENGTH_LONG).show()
        }

    }

    private fun setRegisterActivity() {
        RegisterActivity.start(this)
    }


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

}