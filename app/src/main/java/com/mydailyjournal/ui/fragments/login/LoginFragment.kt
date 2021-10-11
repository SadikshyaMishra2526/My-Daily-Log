package com.mydailyjournal.ui.fragments.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mydailyjournal.R
import com.mydailyjournal.databinding.FragmentLoginBinding
import com.mydailyjournal.utils.UserPrefManager
import com.mydailyjournal.viewmodel.LoginViewModel
import com.mydailyjournal.viewmodel.RegisterViewModel

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var userPrefManager: UserPrefManager

    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userPrefManager = UserPrefManager(requireContext())

        binding.switchToRegister.setOnClickListener { findNavController().navigate(R.id.action_navigation_login_to_navigation_register) }
        binding.btLogin.setOnClickListener {
            val loggerNameEmail = binding.editTextLoginInfo.text.toString()
            val loggerPassword = binding.editTextPassword.text.toString()
            if (validateInput(loggerNameEmail, loggerPassword)) {
                loginLogger(loggerNameEmail, loggerPassword)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please add validate input !!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return root
    }

    private fun validateInput(loggerNameEmail: String, loggerPassword: String): Boolean {
        return !(TextUtils.isEmpty(loggerNameEmail) && TextUtils.isEmpty(loggerPassword))
    }

    private fun loginLogger(loggerNameEmail: String, loggerPassword: String) {
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        registerViewModel.fetchRegisterLogger.observe(viewLifecycleOwner, Observer { user ->
            if (user.isNotEmpty()) {
                for (i in user.indices) {
                    if (user[i].fullName == loggerNameEmail) {
                        if (user[i].password == loggerPassword) {
                            userPrefManager.loggedInLoggerName = loggerNameEmail
                            userPrefManager.loggedInDate   = loggerNameEmail
                          findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
                            Toast.makeText(
                                requireContext(),
                                "Successfully Logged In!!!",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Password Incorrect...Please try again!!!",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "This id is not registered...Please register first",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "No Registration yet...Please be the first one to register...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}