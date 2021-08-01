package com.thinlineit.ctrlf.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.model.User
import com.thinlineit.ctrlf.network.RegistrationService
import com.thinlineit.ctrlf.util.Application
import com.thinlineit.ctrlf.util.Event
import com.thinlineit.ctrlf.util.ResourceProvider
import com.thinlineit.ctrlf.util.isValid
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    lateinit var resourceProvider: ResourceProvider

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
        viewModelScope.launch {
            try {
                val loginResponse = RegistrationService.USER_API.requestLogin(
                    User(email.value ?: "", password.value ?: "")
                )
                Application.preferenceUtil.setString(TOKEN, loginResponse.token)
                Application.preferenceUtil.setString(EMAIL, email.value ?: "")
                Application.preferenceUtil.setString(PASSWORD, password.value ?: "")
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
            loginMessage.value = resourceProvider.getString(R.string.alert_text)
        } else if (!email.value.isValid(EMAILREGEX)) {
            loginMessage.value = resourceProvider.getString(R.string.alert_email)
        } else {
            doLogin()
        }
    }

    companion object {
        private const val TOKEN = "token"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val EMAILREGEX = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    }

}