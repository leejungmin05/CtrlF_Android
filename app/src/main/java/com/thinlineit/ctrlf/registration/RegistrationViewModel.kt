package com.thinlineit.ctrlf.registration

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.repository.UserRepository
import com.thinlineit.ctrlf.util.Event
import com.thinlineit.ctrlf.util.Status
import com.thinlineit.ctrlf.util.addSourceList
import com.thinlineit.ctrlf.util.isValid
import com.thinlineit.ctrlf.util.postEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val userRepository = UserRepository()

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val passwordConfirm = MutableLiveData("")
    val nickName = MutableLiveData("")
    val code = MutableLiveData("")

    val emailStatus = MutableLiveData<Event<Status>>()
    val codeStatus = MutableLiveData<Event<Status>>()
    val nicknameStatus = MutableLiveData<Event<Status>>()
    val passwordStatus = MutableLiveData<Event<Status>>()
    val passwordConfirmStatus = MutableLiveData<Event<Status>>()
    val registerClick = MutableLiveData<Event<Boolean>>()

    val liveDataMerger = MediatorLiveData<Boolean>().apply {
        addSourceList(
            emailStatus,
            codeStatus,
            nicknameStatus,
            passwordStatus,
            passwordConfirmStatus
        ) {
            isSignUpValid()
        }
    }

    val emailMessage = MutableLiveData<Int>(R.string.default_text)
    val nicknameMessage = MutableLiveData<Int>(R.string.default_text)
    val passwordMessage = MutableLiveData<Int>(R.string.default_text)
    val passwordConfirmMessage = MutableLiveData<Int>(R.string.default_text)
    val codeMessage = MutableLiveData<Int>(R.string.default_text)

    val emailInvoke: () -> Unit = this::checkDuplicateEmail
    val codeInvoke: () -> Unit = this::checkCodeValid
    val nicknameInvoke: () -> Unit = this::checkDuplicateNickname
    val passwordInvoke: () -> Unit = this::checkPasswordValid
    val passwordConfirmInvoke: () -> Unit = this::checkPasswordSame

    fun checkPasswordSame() {
        if (!passwordConfirm.value.isValid(PASSWORD_REGEX)) {
            passwordConfirmStatus.value = Event(Status.FAILURE)
            passwordConfirmMessage.postValue(R.string.alert_pwd)
            return
        }
        if (password.value == passwordConfirm.value) {
            passwordConfirmStatus.postEvent(Status.SUCCESS)
            passwordConfirmMessage.postValue(R.string.default_text)
        } else {
            passwordConfirmMessage.postValue(R.string.alert_pwd)
            passwordConfirmStatus.postEvent(Status.FAILURE)
        }
    }

    private fun isSignUpValid(): Boolean =
        emailStatus.value?.equalContent(Status.SUCCESS) ?: false &&
            codeStatus.value?.equalContent(Status.SUCCESS) ?: false &&
            nicknameStatus.value?.equalContent(Status.SUCCESS) ?: false &&
            passwordStatus.value?.equalContent(Status.SUCCESS) ?: false &&
            passwordConfirmStatus.value?.equalContent(Status.SUCCESS) ?: false

    fun checkDuplicateNickname() {
        if (!nickName.value.isValid(NICKNAME_REGEX)) {
            nicknameMessage.postValue(R.string.alert_nickname_valid)
            nicknameStatus.postEvent(Status.FAILURE)
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            if (userRepository.checkNickname(nickName.value.toString())) {
                nicknameStatus.postEvent(Status.SUCCESS)
                nicknameMessage.postValue(R.string.default_text)
            } else {
                nicknameStatus.postEvent(Status.FAILURE)
                nicknameMessage.postValue(R.string.error_nickname)
            }
        }
    }

    fun checkCodeValid() {
        if (code.value.toString().isEmpty()) {
            codeStatus.value = Event(Status.FAILURE)
            codeMessage.postValue(R.string.alert_code)
            return
        }
        if (!code.value.isValid(CODE_REGEX)) {
            codeMessage.postValue(R.string.alert_code_valid)
            codeStatus.value = Event(Status.FAILURE)
            return
        }
        viewModelScope.launch {
            if (userRepository.checkCode(code.value.toString())) {
                codeStatus.postEvent(Status.SUCCESS)
                codeMessage.postValue(R.string.default_text)
            } else {
                codeMessage.postValue(R.string.error_code)
                codeStatus.value = Event(Status.FAILURE)
            }
        }
    }

    fun checkPasswordValid() {
        if (password.value.isValid(PASSWORD_REGEX)) {
            passwordStatus.value = Event(Status.SUCCESS)
            passwordMessage.postValue(R.string.default_text)
        } else {
            passwordMessage.postValue(R.string.alert_pwd_valid)
            passwordStatus.value = Event(Status.FAILURE)
        }
    }

    fun checkDuplicateEmail() {
        if (!email.value.isValid(EMAIL_REGEX)) {
            emailStatus.postEvent(Status.FAILURE)
            emailMessage.postValue(R.string.alert_email)
            return
        }
        viewModelScope.launch {
            if (userRepository.checkDuplicateEmail(email.value.toString())) {
                emailMessage.postValue(R.string.default_text)
                sendAuthEmail()
            } else {
                emailStatus.postEvent(Status.FAILURE)
                emailMessage.postValue(R.string.error_email)
            }
        }
    }

    private fun sendAuthEmail() {
        viewModelScope.launch {
            if (userRepository.sendAuthCode(email.value.toString())) {
                emailStatus.postEvent(Status.SUCCESS)
                emailMessage.postValue(R.string.default_text)
            } else {
                emailMessage.postValue(R.string.alert_email)
                emailStatus.postEvent(Status.FAILURE)
            }
        }
    }

    fun requestSignUp() {
        viewModelScope.launch {
            if (userRepository.requestSignUp(
                    email.value.toString(),
                    code.value.toString(),
                    nickName.value.toString(),
                    password.value.toString(),
                    passwordConfirm.value.toString()
                )
            ) {
                registerClick.postValue(Event(true))
            }
        }
    }

    fun resetEmailCodeValue() {
        email.value = ""
        code.value = ""
    }

    companion object {
        // 숫자, 문자, 특수문자 중 2가지 포함(8~20자)
        private const val PASSWORD_REGEX =
            "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,20}\$"
        private const val EMAIL_REGEX = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
        private const val NICKNAME_REGEX = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{2,10}$"
        private const val CODE_REGEX = "^[a-zA-Z0-9]{8}$"
        private const val SUCCESS = 0
        private const val FAILURE = 1
    }
}
