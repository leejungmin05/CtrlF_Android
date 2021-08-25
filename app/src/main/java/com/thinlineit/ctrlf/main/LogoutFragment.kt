package com.thinlineit.ctrlf.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentLogoutBinding
import com.thinlineit.ctrlf.splash.SplashActivity
import com.thinlineit.ctrlf.util.Application

class LogoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = (DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_logout,
            container,
            false
        ) as FragmentLogoutBinding).apply {
            logoutBtn.setOnClickListener {
                deleteInfo()
                (activity as AppCompatActivity).finishAffinity()
                val intent = Intent(activity, SplashActivity::class.java)
                startActivity(intent)
            }
            cancelBtn.setOnClickListener {
                (activity as AppCompatActivity).onBackPressed()
            }
        }
        return binding.root
    }

    fun deleteInfo() {
        Application.preferenceUtil.setString(EMAIL, "")
        Application.preferenceUtil.setString(PASSWORD, "")
        Application.preferenceUtil.setString(TOKEN, "")
    }

    companion object {
        private const val TOKEN = "token"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }
}