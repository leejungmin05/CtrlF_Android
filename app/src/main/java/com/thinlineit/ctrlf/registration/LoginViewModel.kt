package com.thinlineit.ctrlf.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.model.User
import com.thinlineit.ctrlf.network.RegistrationService
import com.thinlineit.ctrlf.util.Application
import com.thinlineit.ctrlf.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val _loginStatus = MutableLiveData<Event<Boolean>>()
    val loginStatus: LiveData<Event<Boolean>>
        get() = _loginStatus

    val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    val loginMessage = MutableLiveData<String>()

    private val _eventClick = MutableLiveData<Event<Boolean>>()
    val eventClick: LiveData<Event<Boolean>>
        get() = _eventClick

    init {
        _email.value = ""
        _password.value = ""
    }

    private fun doLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginResponse = RegistrationService.USER_API.requestLogin(
                    User(email.value.toString(), password.value.toString())
                )
                val token = loginResponse.token
                Application.prefs.setString(TOKEN, token)
                Application.prefs.setString(EMAIL, email.value.toString())
                Application.prefs.setString(PASSWORD, password.value.toString())
                _loginStatus.postValue(Event(true))
            } catch (e: Exception) {
                loginMessage.postValue(e.message)
            }
        }
    }

    private fun String.isEmailValid(): Boolean {
        val expression = EMAILREGEX
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(this)
        return matcher.matches()
    }

    fun startRegisterActivity() {
        _eventClick.value = Event(true)
    }

    fun loginChk() {
        if (email.value == "" || password.value == "") {
            loginMessage.postValue(NULLMESSAGE)
        } else if (!email.value.toString().isEmailValid()) {
            loginMessage.postValue(EMAILMESSAGE)
        } else {
            doLogin()
        }
    }

    private val NULLMESSAGE = "비밀번호와 아이디를 입력해주세요."
    private val EMAILMESSAGE = "이메일 형식이 올바르지 않습니다."
    private val TOKEN = "token"
    private val EMAIL = "email"
    private val PASSWORD = "password"
    private val EMAILREGEX = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"


}