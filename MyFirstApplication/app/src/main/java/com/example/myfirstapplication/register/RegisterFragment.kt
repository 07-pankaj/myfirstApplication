package com.example.myfirstapplication.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.databinding.FragmentRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class RegisterFragment : Fragment() {

    private lateinit var binding:FragmentRegisterBinding

    val viewModel: RegisterViewModal by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.alreadyHaveAccInRegister.setOnClickListener {
           val action = RegisterFragmentDirections.actionSignUpToLoginIn()
            findNavController().navigate(action)
        }

        binding.backButtonInRegister.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.dialogAlert.value = R.string.zero
        viewModel.dialogAlert.observe(viewLifecycleOwner, Observer {
            popUpDialogBox(viewModel.dialogAlert.value!!)
        })
        return binding.root
    }

    private fun popUpDialogBox(stringId: Int) {

        if (stringId != R.string.zero) {
            MaterialAlertDialogBuilder(binding.root.context)
                .setTitle(resources.getString(stringId))
                .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                    // Respond to positive button press
                }.show()
        }

    }

}