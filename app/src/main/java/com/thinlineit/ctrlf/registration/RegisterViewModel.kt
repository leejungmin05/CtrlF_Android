package com.thinlineit.ctrlf.registration

import androidx.lifecycle.*
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.data.request.AuthEmailRequest
import com.thinlineit.ctrlf.data.request.SignUpRequest
import com.thinlineit.ctrlf.network.RegistrationService
import com.thinlineit.ctrlf.util.Event
import com.thinlineit.ctrlf.util.addSourceList
import com.thinlineit.ctrlf.util.isValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val passwordConfirm = MutableLiveData("")
    val nickName = MutableLiveData("")
    val code = MutableLiveData("")

    val emailStatus = MutableLiveData<Event<Int>>()
    val codeStatus = MutableLiveData<Event<Int>>()
    val nicknameStatus = MutableLiveData<Event<Int>>()
    val pwdStatus = MutableLiveData<Event<Int>>()
    val pwdConfirmStatus = MutableLiveData<Event<Int>>()
    val registerClick = MutableLiveData<Event<Boolean>>()

    val liveDataMerger = MediatorLiveData<Boolean>().apply {
        addSourceList(emailStatus, codeStatus, nicknameStatus, pwdStatus) {
            isSignUpValid()
        }
    }

    val emailMessage = MutableLiveData<Int>(R.string.default_text)
    val nicknameMessage = MutableLiveData<Int>(R.string.default_text)
    val pwdMessage = MutableLiveData<Int>(R.string.default_text)
    val pwd2Message = MutableLiveData<Int>(R.string.default_text)
    val codeMessage = MutableLiveData<Int>(R.string.default_text)
    val emailAuthMessage = MutableLiveData<Int>(R.string.default_text)

    fun checkDuplicateNickname() {
        if (nickName.value.isValid(NICKNAMEREGEX)) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    RegistrationService.USER_API.checkNickname(nickName.value.toString())
                    nicknameStatus.postValue(Event(SUCCESS))
                } catch (e: Exception) {
                    nicknameStatus.postValue(Event(FAILURE))
                    nicknameMessage.postValue(R.string.error_nickname)
                }
            }
        } else {
            nicknameMessage.postValue(R.string.alert_nickname_valid)
            nicknameStatus.postValue(Event(FAILURE))
        }
    }

    fun checkCodeValid() {
        if (code.value.toString() != "") {
            if (code.value.isValid(CODEREGEX)) {
                viewModelScope.launch {
                    try {
                        //TODO : code check API
                        codeStatus.value = Event(SUCCESS)
                    } catch (e: Exception) {
                        codeMessage.postValue(R.string.error_code)
                    }
                }
            } else {
                codeMessage.postValue(R.string.alert_code_valid)
                codeStatus.value = Event(FAILURE)
            }
        } else {
            codeStatus.value = Event(FAILURE)
            codeMessage.postValue(R.string.alert_code)
        }
    }


    val checkPasswordSame: () -> Unit = {
        if (passwordConfirm.value.isValid(PWDREGEX)) {
            if (password.value == passwordConfirm.value) {
                pwdConfirmStatus.value = Event(SUCCESS)
            } else {
                pwdConfirmStatus.value = Event(FAILURE)
                pwd2Message.postValue(R.string.alert_pwd)
            }
        }
    }


    fun checkPasswordValid() {
        if (password.value.isValid(PWDREGEX)) {
            pwdStatus.value = Event(SUCCESS)
        } else {
            pwdMessage.postValue(R.string.alert_pwd_valid)
            pwdStatus.value = Event(FAILURE)
        }
    }

    fun checkDuplicateEmail() {
        viewModelScope.launch {
            if (email.value.isValid(EMAILREGEX)) {
                try {
                    RegistrationService.USER_API.checkEmail(email.value.toString())
                    emailStatus.postValue(Event(SUCCESS))
                } catch (e: Exception) {
                    emailStatus.postValue(Event(FAILURE))
                    emailMessage.postValue(R.string.error_email)
                }
            } else {
                emailStatus.postValue(Event(FAILURE))
                emailMessage.postValue(R.string.alert_email)
            }
        }
    }

    fun sendAuthEmail() {
        viewModelScope.launch {
            try {
                RegistrationService.USER_API.authEmail(AuthEmailRequest(email.value.toString()))
                emailStatus.postValue(Event(SUCCESS))
            } catch (e: Exception) {
                emailMessage.postValue(R.string.alert_email)
            }
        }
    }

    private fun isSignUpValid(): Boolean =
        emailStatus.value == Event(SUCCESS) && codeStatus.value == Event(SUCCESS) && nicknameStatus.value == Event(
            SUCCESS
        ) && pwdStatus.value == Event(SUCCESS) && pwdConfirmStatus.value == Event(SUCCESS)

    fun requestSignUp() {
        viewModelScope.launch {
            try {
                RegistrationService.USER_API.requestSignUp(
                    SignUpRequest(
                        email.value ?: return@launch,
                        code.value ?: return@launch,
                        nickName.value ?: return@launch,
                        password.value ?: return@launch,
                        passwordConfirm.value ?: return@launch
                    )
                )
                registerClick.postValue(Event(true))
            } catch (e: Exception) {
            }
        }
    }

    companion object {
        // 숫자, 문자, 특수문자 중 2가지 포함(8~20자)
        private const val PWDREGEX =
            "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,20}\$"
        private const val EMAILREGEX = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
        private const val NICKNAMEREGEX = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{2,10}$"
        private const val CODEREGEX = "^[0-9]{6}$"
        private const val SUCCESS = 0
        private const val FAILURE = 1
    }
}