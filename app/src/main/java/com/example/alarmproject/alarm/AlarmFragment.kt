package com.example.alarmproject.alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import com.example.alarmproject.databinding.FragmentMorningBinding

class AlarmFragment() : Fragment() {
    private lateinit var binding: FragmentMorningBinding
    private val viewModel: BannerViewModel by lazy {
        ViewModelProvider(this, BannerViewModelFactory(requireContext())).get(BannerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_morning,container,false)
        binding.viewModel = viewModel

        


        return binding.root
    }

}
