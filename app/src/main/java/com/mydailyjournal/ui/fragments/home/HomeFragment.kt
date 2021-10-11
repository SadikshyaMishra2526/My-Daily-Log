package com.mydailyjournal.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydailyjournal.R
import com.mydailyjournal.databinding.FragmentHomeBinding
import com.mydailyjournal.ui.adapter.LogDetailsAdapter
import com.mydailyjournal.ui.fragments.AddDailyJournal
import com.mydailyjournal.utils.UserPrefManager
import com.mydailyjournal.viewmodel.LogViewModel
import com.mydailyjournal.viewmodel.HomeViewModel
import com.mydailyjournal.viewmodel.RegisterViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var userPrefManager: UserPrefManager? = null

    private lateinit var logViewModel: LogViewModel
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        userPrefManager = UserPrefManager(context)

        binding.displayAddLogDialog.setOnClickListener {
            var addDailyJournal = AddDailyJournal()
            addDailyJournal.show(childFragmentManager, addDailyJournal.tag)

        }
        binding.diaristLogin.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_navigation_login) }

        if (userPrefManager!!.loggedInLoggerName != null) {
            binding.userInfo.text = userPrefManager!!.loggedInLoggerName
        } else {
            binding.userInfo.text = "Welcome Please Sign In to start your journal"
        }



        val adapter = LogDetailsAdapter()
        binding.logViewRecycler.adapter=adapter
        binding.logViewRecycler.layoutManager = LinearLayoutManager(requireContext())

        // logViewModel
        logViewModel = ViewModelProvider(this).get(LogViewModel::class.java)
        logViewModel.fetchAllData.observe(viewLifecycleOwner, Observer { logs ->
            adapter.setData(logs)
        })


        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        registerViewModel.fetchRegisterLogger.observe(viewLifecycleOwner, Observer { user ->
          if(user.isNotEmpty()){
              binding.displayAddLogDialog.visibility=View.VISIBLE
             binding.noRegistrationView.visibility=View.GONE
          }else{
              binding.displayAddLogDialog.visibility=View.INVISIBLE
              binding.noRegistrationView.visibility=View.VISIBLE
              binding.registerLogger.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_navigation_register) }
          }
        })



        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}