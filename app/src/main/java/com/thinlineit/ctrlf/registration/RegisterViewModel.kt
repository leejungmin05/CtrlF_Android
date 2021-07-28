package com.thinlineit.ctrlf.registration

import androidx.lifecycle.*
import com.thinlineit.ctrlf.data.request.AuthEmailRequest
import com.thinlineit.ctrlf.data.request.SignUpRequest
import com.thinlineit.ctrlf.network.RegistrationService
import com.thinlineit.ctrlf.util.addSourceList
import com.thinlineit.ctrlf.util.isValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel : ViewModel() {

    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val passwordConfirm = MutableLiveData<String>()
    val nickName = MutableLiveData<String>()
    val code = MutableLiveData<String>()

    val emailStatus = MutableLiveData<Int>()
    val codeStatus = MutableLiveData<Int>()
    val nicknameStatus = MutableLiveData<Int>()
    val pwdStatus = MutableLiveData<Int>()

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

    val isEmailEnabled = Transformations.map(emailStatus) { it == Success }

    val checkDuplicateNickname: () -> Unit = {
        if (nickName.value.toString().isValid(NICKNAMEREGEX)) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    RegistrationService.USER_API.checkNickname(nickName.value.toString())
                    nicknameStatus.value = Success
                    nicknameMessage.postValue("")
                } catch (e: Exception) {
                    nicknameStatus.value = Failure
                    nicknameMessage.postValue(e.message)
                }
            }
        } else {
            nicknameMessage.postValue(NICKNAMEMESSAGE)
            nicknameStatus.value = Failure
        }
    }

    val checkCodeValid: () -> Unit = {
        if (code.value.toString() != "") {
            if (code.value!!.isValid(CODEREGEX)) {
                codeStatus.value = Success
                codeMessage.postValue("")
            } else {
                codeMessage.postValue(CODEMESSAGE2)
                codeStatus.value = Failure
            }
        } else {
            codeStatus.value = Failure
            codeMessage.postValue(CODEMESSAGE)
        }
    }

    val checkPasswordValid: () -> Unit = {
        if (password.value!!.isValid(PWDREGEX)) {
            if (password.value == passwordConfirm.value) {
                pwdStatus.value = Success
                pwdMessage.postValue("")
            } else {
                pwdStatus.value = 1
                pwdMessage.postValue(PWDMESSAGE2)
            }
        } else {
            pwdMessage.postValue(PWDMESSAGE)
            pwdStatus.value = 1
        }
    }

    fun checkDuplicateEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            if (email.value.toString().isValid(EMAILREGEX)) {
                try {
                    RegistrationService.USER_API.checkEmail(email.value.toString())
                    emailStatus.postValue(Success)
                    emailMessage.postValue("")
                } catch (e: Exception) {
                    emailStatus.postValue(Failure)
                    emailMessage.postValue(e.message)
                }
            } else {
                emailStatus.postValue(Failure)
                emailMessage.postValue(EMAILMESSAGE)
            }
        }
    }


    fun sendAuthEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                RegistrationService.USER_API.authEmail(AuthEmailRequest(email.value.toString()))
                emailAuthMessage.postValue(EMAILAUTH)
            } catch (e: Exception) {
                emailMessage.postValue(EMAILMESSAGE)
            }
        }
    }

    private fun isSignUpValid(): Boolean =
        emailStatus.value == Success && codeStatus.value == Success && nicknameStatus.value == Success && pwdStatus.value == Success

    fun requestSignUp() {
        viewModelScope.launch(Dispatchers.IO) {
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
            } catch (e: Exception) {
                codeStatus.postValue(Failure)
                codeMessage.postValue(CODEMESSAGE2)
            }

        }
    }

    companion object {
        private val EMAILMESSAGE = "이메일 형식이 올바르지 않습니다."
        private val EMAILAUTH = "인증 코드를 발송했습니다."
        private val NICKNAMEMESSAGE = "닉네임은 2~10자, 공백이나 특수문자는 허용하지 않습니다"
        private val PWDMESSAGE = "영문, 숫자, 특수문자 중 2가지 이상 조합하여 8~20자이내로 기입해주세요"
        private val PWDMESSAGE2 = "비밀번호가 일치하지 않습니다"
        private val CODEMESSAGE = "코드를 입력하세요"
        private val CODEMESSAGE2 = "코드가 일치하지 않습니다"
        private val EMAILREGEX = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
        // 8~20 영문 대소문자와 최소 1개의 숫자 혹은 특수문자 포함
        private val PWDREGEX =
            "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{8,20}\$"
        private val NICKNAMEREGEX = "^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]{2,10}$"
        private val CODEREGEX = "^[0-9]{6}$"
        private val Success = 0
        private val Failure = 1
    }
}