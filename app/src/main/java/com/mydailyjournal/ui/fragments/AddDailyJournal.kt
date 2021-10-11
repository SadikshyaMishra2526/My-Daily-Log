package com.mydailyjournal.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mydailyjournal.databinding.FragmentAddJournalBinding

import android.text.TextUtils
import android.widget.Toast
import com.mydailyjournal.database.LogDetails
import com.mydailyjournal.utils.UserPrefManager
import com.mydailyjournal.viewmodel.LogViewModel
import java.text.SimpleDateFormat
import java.util.*


class AddDailyJournal : BottomSheetDialogFragment() {
    private var _binding: FragmentAddJournalBinding? = null
    private val binding get() = _binding!!
    private var userPrefManager: UserPrefManager? = null

    private lateinit var logViewModel: LogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddJournalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        logViewModel = ViewModelProvider(this).get(LogViewModel::class.java)


        userPrefManager = UserPrefManager(context)
        binding.editTextLoggedDate.setText(getCurrentTimeStamp())

        binding.btAddJournal.setOnClickListener {
            insertDataToLogTable()
        }

        return root
    }

    private fun insertDataToLogTable() {
        val logTitle = binding.editTextLogTitle.text.toString()
        val loggedDate = binding.editTextLoggedDate.text.toString()
        val loggedDescription = binding.editTextLogDescription.text.toString()
        val loggedImage = ""
        val joinedDate =getCurrentTimeStamp()
        val addedBy= userPrefManager?.loggedInLoggerName


        if (checkValidation(logTitle, loggedDate, loggedDescription, loggedImage)) {
            val logRecorded =
                LogDetails(0, logTitle, loggedDate, loggedDescription, loggedImage, joinedDate!!,addedBy!!)
            logViewModel.addDailyLogger(logRecorded)
            Toast.makeText(requireContext(), "Successfully Added today's Log !!!", Toast.LENGTH_SHORT)
                .show()
           dismiss()
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill all the field before registration !!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun checkValidation(
        logTitle: String,
        loggedDate: String,
        loggedDescription: String,
        loggedImage: String
    ): Boolean {
        return !(TextUtils.isEmpty(logTitle) && TextUtils.isEmpty(loggedDate) && TextUtils.isEmpty(
            loggedDescription
        ) && TextUtils.isEmpty(loggedImage))
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