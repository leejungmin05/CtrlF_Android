package com.thinlineit.ctrlf.repository

import com.thinlineit.ctrlf.model.User
import com.thinlineit.ctrlf.repository.network.RegistrationService
import com.thinlineit.ctrlf.util.Application
import java.lang.Exception

class UserRepository {

    suspend fun doLogin(email: String, password: String): Boolean =
        try {
            val loginResponse = RegistrationService.USER_API.requestLogin(
                User(email, password)
            )
            Application.preferenceUtil.setString(TOKEN, loginResponse.token)
            Application.preferenceUtil.setString(EMAIL, email)
            Application.preferenceUtil.setString(PASSWORD, password)
            true
        } catch (e: Exception) {
            false
        }

    suspend fun mayLogin(): Boolean =
        if (checkLogin()) {
            doLogin(
                Application.preferenceUtil.getString(EMAIL, ""),
                Application.preferenceUtil.getString(
                    PASSWORD, ""
                )
            )
        } else {
            false
        }

    fun checkLogin(): Boolean {
        return Application.preferenceUtil.getString(EMAIL, "") != ""
    }

    companion object {
        private const val TOKEN = "token"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}