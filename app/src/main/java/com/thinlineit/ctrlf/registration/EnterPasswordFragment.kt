package com.thinlineit.ctrlf.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.thinlineit.ctrlf.R
import com.thinlineit.ctrlf.databinding.FragmentPasswordBinding
import com.thinlineit.ctrlf.util.Status
import com.thinlineit.ctrlf.util.base.BaseFragment
import com.thinlineit.ctrlf.util.observeIfNotHandled
import com.thinlineit.ctrlf.util.setBackground
import kotlinx.android.synthetic.main.fragment_password.*


class EnterPasswordFragment : BaseFragment<FragmentPasswordBinding>(R.layout.fragment_password) {
    private lateinit var navController: NavController
    private val viewModel by activityViewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = this@EnterPasswordFragment.viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val anim = AnimationUtils.loadAnimation(context, R.anim.shake_animation)
        navController = Navigation.findNavController(view)

        viewModel.passwordStatus.observeIfNotHandled(viewLifecycleOwner) {
            if (it == Status.FAILURE.ordinal) {
                binding.regPassword.setBackground(R.drawable.border_edittext_error)
                binding.regPassword.startAnimation(anim)
            } else {
                navController.navigate(R.id.action_registerPasswordFragment_to_registerConfirmPasswordFragment)
            }
        }

        binding.backBtn.setOnClickListener {
            navController.navigate(R.id.action_registerPasswordFragment_to_registerNicknameFragment)
        }
    }
}