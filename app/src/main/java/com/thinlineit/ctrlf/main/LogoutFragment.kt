package com.thinlineit.ctrlf.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thinlineit.ctrlf.databinding.FragmentLogoutBinding
import com.thinlineit.ctrlf.splash.SplashActivity
import com.thinlineit.ctrlf.util.Application

class LogoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLogoutBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            logoutBtn.setOnClickListener {
                deleteInfo()
                requireActivity().finishAffinity()
                SplashActivity.relaunch(requireContext())
            }
            cancelBtn.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        return binding.root
    }

    private fun deleteInfo() {
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
