package com.thinlineit.ctrlf.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.repository.UserRepository
import com.thinlineit.ctrlf.util.Event
import com.thinlineit.ctrlf.util.Status
import com.thinlineit.ctrlf.util.isValid
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val userRepository: UserRepository by lazy {
        UserRepository()
    }

    private val _eventClick = MutableLiveData<Event<Boolean>>()
    val eventClick: LiveData<Event<Boolean>>
        get() = _eventClick

    val loginStatus = MutableLiveData<Event<Int>>()
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val loginMessage = MutableLiveData<Int>(R.string.default_text)

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                userRepository.doLogin(email, password)
                loginStatus.value = Event(Status.SUCCESS.ordinal)
            } catch (e: Exception) {
                loginMessage.postValue(R.string.alert_login)
            }
        }
    }

    fun startRegisterActivity() {
        _eventClick.value = Event(true)
    }

    fun checkLogin() {
        val emailValue = email.value ?: ""
        val passwordValue = password.value ?: ""

        if (emailValue == "" || passwordValue == "") {
            loginMessage.postValue(R.string.alert_text)
        } else if (!emailValue.isValid(EMAILREGEX)) {
            loginMessage.postValue(R.string.alert_email)
        } else {
            login(emailValue,passwordValue)
        }
    }

    companion object {
        private const val EMAILREGEX = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    }
}