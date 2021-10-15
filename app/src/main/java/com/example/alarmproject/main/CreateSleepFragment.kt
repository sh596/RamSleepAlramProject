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
import com.example.alarmproject.dialog.RadioDialog

class CreateSleepFragment : Fragment() {
    private lateinit var binding: FragmentCreateSleepBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[CreateSleepViewModel::class.java]
    }

    private val mainViewModel by lazy {
        ViewModelProvider(requireActivity())[AlarmViewModel::class.java]
    }

    private val adapter = CreateSleepAdapter(viewModel.itemList.value!!)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_sleep,container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val view = binding.root

        viewModel.setWakeTime(mainViewModel.wakeUpTime.value!!)

        setRecyclerView()

        return view
    }
    fun setEarlyAlarmDialog(view :View){
        val earlyAlarmDialog = RadioDialog(view.context,"미리 알림", mutableListOf("10분", "20분", "30분"),earlyAlarmPosClick)
        earlyAlarmDialog.show()
    }

    private fun setRecyclerView() {
        viewModel.setItemList(mainViewModel.wakeUpTime.value!!)
        binding.createSleepRecyclerView.adapter = adapter
    }

    private fun createAlarm(view: View){
        for(i in adapter.checkList.indices){
            if(adapter.checkList[i]){
                mainViewModel.sleepTime.value = mainViewModel.wakeUpTime.value!! - (270 + 90 * i)
                break
            }
        }
    }

    private val earlyAlarmPosClick = { position: Int ->
        mainViewModel.setEarlyAlarmTime(position)
    }

}