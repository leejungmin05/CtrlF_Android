package com.thinlineit.ctrlf.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thinlineit.ctrlf.model.User
import com.thinlineit.ctrlf.network.RegistrationService
import com.thinlineit.ctrlf.util.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    val eventClick: MutableLiveData<Boolean> = MutableLiveData()
    val alertLiveData = MutableLiveData<String>()

    init {
        _email.value = ""
        _password.value = ""
    }


    fun loginChk() {
        if (_email.value == "" || _password.value == "") {
            _loginStatus.value = false
        } else {
            doLogin()
        }
    }

    private fun doLogin() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val loginResponse = RegistrationService.USER_API.requestLogin(
                    User(email.value.toString(), password.value.toString())
                )
                val token = loginResponse.token
                Application.prefs.setString("token", token)
                Application.prefs.setString("email", _email.value.toString())
                Application.prefs.setString("password", _password.value.toString())
                _loginStatus.postValue(true)
            } catch (e: Exception) {
                alertLiveData.postValue(e.message)

            }
        }
    }

    fun startRegisterActivity() {
        eventClick.value = true
    }


}