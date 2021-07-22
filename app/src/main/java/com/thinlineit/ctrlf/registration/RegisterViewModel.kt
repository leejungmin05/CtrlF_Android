package com.thinlineit.ctrlf.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thinlineit.ctrlf.data.request.AuthEmailRequest
import com.thinlineit.ctrlf.data.request.SignUpRequest
import com.thinlineit.ctrlf.network.RegistrationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel : ViewModel() {
    val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    val _passwordConfirm = MutableLiveData<String>()
    val passwordConfirm: LiveData<String>
        get() = _passwordConfirm

    val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String>
        get() = _nickName

    val _code = MutableLiveData<String>()
    val code: LiveData<String>
        get() = _code

    val alertLiveData = MutableLiveData<String>()

    val isEmailEnabled = MutableLiveData<Boolean>(false)
    val isRegisterEnabled = MutableLiveData<Boolean>(false)

    init {
        _email.value = ""
        _password.value = ""
        _passwordConfirm.value = ""
        _nickName.value = ""
        _code.value = ""
    }

    fun duplicateEmail() {
        CoroutineScope(Dispatchers.IO).launch {
            // 이메일 형식 체크 필요!
            try {
                val duplicateEmail = RegistrationService.USER_API.emailChk(email.value.toString())
                alertLiveData.postValue(duplicateEmail.message)
                isEmailEnabled.postValue(true)
            } catch (e: Exception) {
                alertLiveData.postValue(e.message)
                isEmailEnabled.postValue(false)
            }
        }
    }

    fun authEmail() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val authEmail =
                    RegistrationService.USER_API.authEmail(AuthEmailRequest(email.value.toString()))
                alertLiveData.postValue(authEmail.message)
            } catch (e: Exception) {
                alertLiveData.postValue(e.message)
            }
        }
    }

    fun duplicateNickname() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val duplicateNickname =
                    RegistrationService.USER_API.nicknameChk(nickName.value.toString())
                alertLiveData.postValue(duplicateNickname.message)
                isRegisterEnabled.postValue(true)
            } catch (e: Exception) {
                alertLiveData.postValue(e.message)
                isRegisterEnabled.postValue(false)
            }
        }
    }

    fun chkPassword(): Boolean {
        return password.value == passwordConfirm.value
    }

    fun requestSignUp() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                RegistrationService.USER_API.requestSignUp(
                    SignUpRequest(
                        email.value.toString(),
                        code.value.toString(),
                        nickName.value.toString(),
                        password.value.toString(),
                        passwordConfirm.value.toString()
                    )
                )
            } catch (e: Exception) {
                alertLiveData.postValue(e.message)
            }
        }
    }

}