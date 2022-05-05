package com.example.myfirstapplication.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.databinding.FragmentLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collectLatest


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

       binding.signUpTextInLogin.setOnClickListener {
           val action = LoginFragmentDirections.actionLoginInToSignUp()
            findNavController().navigate(action)
        }

        binding.backButtonInLogin.setOnClickListener {
            findNavController().navigateUp()
        }


        lifecycleScope.launchWhenCreated {
            viewModel.dialogAlert.collectLatest {
                popUpDialogBox(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.loginInSuccessfully.collectLatest {
                val action = LoginFragmentDirections.actionLoginFragmentToChildrenFragment()
                findNavController().navigate(action)
            }
        }



        return binding.root
    }


    private fun popUpDialogBox(stringId:Int)
    {
            MaterialAlertDialogBuilder(binding.root.context)
                .setTitle(resources.getString(stringId))
                .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                    // Respond to positive button press
                }.show()
    }


}