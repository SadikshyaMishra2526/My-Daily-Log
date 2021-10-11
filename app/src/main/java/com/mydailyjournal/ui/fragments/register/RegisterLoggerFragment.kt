package com.mydailyjournal.ui.fragments.register

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mydailyjournal.R
import com.mydailyjournal.database.DailyLogger
import com.mydailyjournal.databinding.FragmentRegisterBinding
import com.mydailyjournal.utils.GeneralUtils
import com.mydailyjournal.utils.UserPrefManager
import com.mydailyjournal.viewmodel.RegisterViewModel
import java.text.SimpleDateFormat
import java.util.*

class RegisterLoggerFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var userPrefManager: UserPrefManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
           registerViewModel=ViewModelProvider(this).get(RegisterViewModel::class.java)

        userPrefManager= UserPrefManager(requireContext())
        binding.btRegister.setOnClickListener {
            insertDataToLoggerTable()
        }



        return root
    }

    private fun insertDataToLoggerTable() {
        val fullName=binding.editTextFullName.text.toString()
        val emailAddress=binding.editTextEmail.text.toString()
        val password=binding.editTextPassword.text.toString()
        val age=binding.editTextAge.text.toString().toInt()
        val joinedDate=getCurrentTimeStamp()
        if(checkValidation(fullName,emailAddress,password,age)){
            val loggerRegistration=DailyLogger(0,fullName,emailAddress,password,age, joinedDate!!)
            registerViewModel.addDailyLogger(loggerRegistration)

            userPrefManager.loggedInLoggerName = fullName
            userPrefManager.loggedInDate   = getCurrentTimeStamp()

            Toast.makeText(requireContext(),"Successfully registered!!!",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_navigation_register_to_navigation_home)
        }else{
            Toast.makeText(requireContext(),"Please fill all the field before registration !!!",Toast.LENGTH_SHORT).show()

        }
    }
     fun checkValidation(fullName:String,emailAddress:String,password:String,age:Int):Boolean{
        return !(TextUtils.isEmpty(fullName)&&TextUtils.isEmpty(emailAddress)&&TextUtils.isEmpty(password)&&TextUtils.isEmpty(
            age.toString()
        ))
    }

     private fun getCurrentTimeStamp(): String? {
        val sdf = SimpleDateFormat("yy/MM/dd  HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}