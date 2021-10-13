package com.example.alarmproject.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import com.example.alarmproject.databinding.FragmentCreateSleepBinding

class CreateSleepFragment : Fragment() {

    private lateinit var binding: FragmentCreateSleepBinding


    private val viewModel by lazy {
        ViewModelProvider(this)[CreateSleepViewModel::class.java]
    }

    private val mainViewModel by lazy {
        ViewModelProvider(requireActivity())[AlarmViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_sleep,container, false,)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        val view = binding.root

        viewModel.setWakeTime(mainViewModel.wakeUpTime.value!!)



        return view
    }

}