package com.thinlineit.ctrlf.registration

import androidx.lifecycle.*
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.data.request.AuthEmailRequest
import com.thinlineit.ctrlf.data.request.SignUpRequest
import com.thinlineit.ctrlf.repository.network.RegistrationService
import com.thinlineit.ctrlf.util.Event
import com.thinlineit.ctrlf.util.ResourceProvider
import com.thinlineit.ctrlf.util.addSourceList
import com.thinlineit.ctrlf.util.isValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel : ViewModel() {
    lateinit var resourceProvider: ResourceProvider

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val passwordConfirm = MutableLiveData("")
    val nickName = MutableLiveData("")
    val code = MutableLiveData("")

    val emailStatus = MutableLiveData<Int>()
    val codeStatus = MutableLiveData<Int>()
    val nicknameStatus = MutableLiveData<Int>()
    val pwdStatus = MutableLiveData<Int>()
    val registerClick = MutableLiveData<Event<Boolean>>()

    val liveDataMerger = MediatorLiveData<Boolean>().apply {
        addSourceList(emailStatus, codeStatus, nicknameStatus, pwdStatus) {
            isSignUpValid()
        }
    }

    val emailMessage = MutableLiveData<String>()
    val nicknameMessage = MutableLiveData<String>()
    val pwdMessage = MutableLiveData<String>()
    val codeMessage = MutableLiveData<String>()
    val emailAuthMessage = MutableLiveData<String>()

    val isEmailEnabled = Transformations.map(emailStatus) { it == SUCCESS }

    val checkDuplicateNickname: () -> Unit = {
        if (nickName.value.isValid(NICKNAMEREGEX)) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    RegistrationService.USER_API.checkNickname(nickName.value.toString())
                    nicknameStatus.postValue(SUCCESS)
                    nicknameMessage.postValue("")
                } catch (e: Exception) {
                    nicknameStatus.postValue(FAILURE)
                    nicknameMessage.postValue(e.message)
                }
            }
        } else {
            nicknameMessage.postValue(resourceProvider.getString(R.string.alert_nickname_valid))
            nicknameStatus.postValue(FAILURE)
        }
    }

    val checkCodeValid: () -> Unit = {
        if (code.value.toString() != "") {
            if (code.value.isValid(CODEREGEX)) {
                codeStatus.value = SUCCESS
                codeMessage.postValue("")
            } else {
                codeMessage.postValue(resourceProvider.getString(R.string.alert_code_valid))
                codeStatus.value = FAILURE
            }
        } else {
            codeStatus.value = FAILURE
            codeMessage.postValue(resourceProvider.getString(R.string.alert_code))
        }
    }

    val checkPasswordValid: () -> Unit = {
        if (password.value.isValid(PWDREGEX)) {
            if (password.value == passwordConfirm.value) {
                pwdStatus.value = SUCCESS
                pwdMessage.postValue("")
            } else {
                pwdStatus.value = 1
                pwdMessage.postValue(resourceProvider.getString(R.string.alert_pwd))
            }
        } else {
            pwdMessage.postValue(resourceProvider.getString(R.string.alert_pwd_valid))
            pwdStatus.value = 1
        }
    }

    fun checkDuplicateEmail() {
        viewModelScope.launch {
            if (email.value.isValid(EMAILREGEX)) {
                try {
                    RegistrationService.USER_API.checkEmail(email.value.toString())
                    emailStatus.postValue(SUCCESS)
                    emailMessage.postValue("")
                } catch (e: Exception) {
                    emailStatus.postValue(FAILURE)
                    emailMessage.postValue(e.message)
                }
            } else {
                emailStatus.postValue(FAILURE)
                emailMessage.postValue(resourceProvider.getString(R.string.alert_email))
            }
        }
    }

    fun sendAuthEmail() {
        viewModelScope.launch {
            try {
                RegistrationService.USER_API.authEmail(AuthEmailRequest(email.value.toString()))
                emailAuthMessage.postValue(resourceProvider.getString(R.string.alert_email_auth))
            } catch (e: Exception) {
                emailMessage.postValue(resourceProvider.getString(R.string.alert_email))
            }
        }
    }

    private fun isSignUpValid(): Boolean =
        emailStatus.value == SUCCESS && codeStatus.value == SUCCESS && nicknameStatus.value == SUCCESS && pwdStatus.value == SUCCESS

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
                codeStatus.postValue(FAILURE)
                codeMessage.postValue(resourceProvider.getString(R.string.alert_code_valid))
            }
        }
    }

    companion object {
        // 8~20 영문 대소문자와 최소 1개의 숫자 혹은 특수문자 포함
        private const val PWDREGEX =
            "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,20}\$"
        private const val EMAILREGEX = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
        private const val NICKNAMEREGEX = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{2,10}$"
        private const val CODEREGEX = "^[0-9]{6}$"
        private const val SUCCESS = 0
        private const val FAILURE = 1
    }
}