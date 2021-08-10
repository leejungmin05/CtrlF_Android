package com.thinlineit.ctrlf.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentConfirmPasswordBinding
import com.thinlineit.ctrlf.util.base.BaseFragment
import com.thinlineit.ctrlf.util.observeIfNotHandled
import com.thinlineit.ctrlf.util.setBackground


class RegisterConfirmPasswordFragment :
    BaseFragment<FragmentConfirmPasswordBinding>(R.layout.fragment_confirm_password) {
    private lateinit var navController: NavController
    private val viewModel by activityViewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = this@RegisterConfirmPasswordFragment.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewModel.pwdConfirmStatus.observeIfNotHandled(viewLifecycleOwner) {
            if (it == FAILURE) {
                binding.regPassword2.setBackground(R.drawable.border_edittext_error)
            }
        }

        viewModel.registerClick.observeIfNotHandled(this) {
            navController.navigate(R.id.action_registerConfirmPasswordFragment_to_completeRegisterActivity)
        }

        binding.backBtn.setOnClickListener {
            navController.navigate(R.id.action_registerConfirmPasswordFragment_to_registerPasswordFragment)
        }
    }

    companion object {
        private const val FAILURE = 1
    }
}