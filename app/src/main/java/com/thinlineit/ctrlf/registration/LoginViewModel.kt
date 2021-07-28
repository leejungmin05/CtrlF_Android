package com.thinlineit.ctrlf.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.model.User
import com.thinlineit.ctrlf.network.RegistrationService
import com.thinlineit.ctrlf.util.Application
import com.thinlineit.ctrlf.util.Event
import com.thinlineit.ctrlf.util.isValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val _loginStatus = MutableLiveData<Event<Boolean>>()
    val loginStatus: LiveData<Event<Boolean>>
        get() = _loginStatus

    private val _eventClick = MutableLiveData<Event<Boolean>>()
    val eventClick: LiveData<Event<Boolean>>
        get() = _eventClick

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val loginMessage = MutableLiveData<String>()

    private fun doLogin() {
        viewModelScope.launch{
            try {
                val loginResponse = RegistrationService.USER_API.requestLogin(
                    User(email.value?:"", password.value?:"")
                )
                Application.preferenceUtil.setString(TOKEN, loginResponse.token)
                Application.preferenceUtil.setString(EMAIL, email.value?:"")
                Application.preferenceUtil.setString(PASSWORD, password.value?:"")
                _loginStatus.value = Event(true)
            } catch (e: Exception) {
                loginMessage.postValue(e.message)
            }
        }
    }

    fun startRegisterActivity() {
        _eventClick.value = Event(true)
    }

    fun checkLogin() {
        if (email.value == "" || password.value == "") {
            loginMessage.value = NULLMESSAGE
        } else if (!email.value.toString().isValid(EMAILREGEX)) {
            loginMessage.value = EMAILMESSAGE
        } else {
            doLogin()
        }
    }

    companion object {
        private val NULLMESSAGE = "비밀번호와 아이디를 입력해주세요."
        private val EMAILMESSAGE = "이메일 형식이 올바르지 않습니다."
        private val TOKEN = "token"
        private val EMAIL = "email"
        private val PASSWORD = "password"
        private val EMAILREGEX = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    }

}