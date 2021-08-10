package com.thinlineit.ctrlf.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentRegisterBackBinding
import com.thinlineit.ctrlf.util.base.BaseFragment


class RegisterBackFragment : BaseFragment<FragmentRegisterBackBinding>(R.layout.fragment_register_back) {
    private lateinit var navController: NavController
    private val viewModel by activityViewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = this@RegisterBackFragment.viewModel

        binding.backBtn.setOnClickListener {
            navController.navigate(R.id.action_registerBackFragment_to_registerEmailFragment)
        }

        binding.cancelBtn.setOnClickListener {
            navController.navigate(R.id.action_registerBackFragment_to_registerNicknameFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }


}