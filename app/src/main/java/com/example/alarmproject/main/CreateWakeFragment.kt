package com.example.alarmproject.main

import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import com.example.alarmproject.databinding.FragmentCreateWakeBinding
import com.example.alarmproject.dialog.RadioDialog
import com.example.alarmproject.dialog.RadioDialogInterface
import com.example.alarmproject.utils.Constant

class CreateWakeFragment : Fragment(), RadioDialogInterface {

    private lateinit var binding: FragmentCreateWakeBinding
    private lateinit var vibrationDialog: RadioDialog
    private lateinit var againAlarmTimeDialog: RadioDialog

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[AlarmViewModel::class.java]
    }

    private val wakeViewModel by lazy {
        ViewModelProvider(this)[CreateWakeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_wake, container, false)
        binding.viewModel = wakeViewModel
        binding.lifecycleOwner = this

        val view = binding.root

        wakeViewModel.setAgainAlarmTime(viewModel.againAlarmTime.value!!)
        wakeViewModel.setVibration(viewModel.vibration.value!!)

        setDialog()

        binding.createWakeAgainAlarmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                wakeViewModel.setAgainAlarmTime(Constant.AGAIN_ALARM_TIME_1)
            }else{
                wakeViewModel.setAgainAlarmTime(0)

            }
        }

        binding.createWakeNextButton.setOnClickListener {
            viewModel.apply {
                wakeUpTime.value = getTime()
            }
            (activity as CreateAlarmActivity).nextFragment()
        }

        binding.createWakeBackButton.setOnClickListener {
            (activity as CreateAlarmActivity).finish()
        }

        return view
    }

    private fun setDialog(){
        binding.createWakeBellLayout.setOnClickListener {
            vibrationDialog = RadioDialog(requireView().context, "진동 설정", mutableListOf("기본", "짧은", "긴", "시끄러운"), this,Constant.VIBRATION_DIALOG)
            vibrationDialog.show()
        }

        binding.createWakeAgainAlarmLayout.setOnClickListener {
            againAlarmTimeDialog = RadioDialog(requireView().context,"알람 간격", mutableListOf("5분", "10분", "15분", "20분"), this,Constant.AGAIN_TIME_DIALOG)
            againAlarmTimeDialog.show()
        }

    }

    private fun getTime(): Int? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            (binding.createWakeTimePicker.hour * 100) + binding.createWakeTimePicker.minute
        } else {
            binding.createWakeTimePicker.currentHour * 100 + binding.createWakeTimePicker.currentMinute
        }
    }


    override fun posClick(viewType: Int) {
        if (viewType == Constant.VIBRATION_DIALOG) {
            viewModel.setVibration(vibrationDialog.position)
            wakeViewModel.setVibration(viewModel.vibration.value!!)
        } else {
            viewModel.setAgainAlarmTime(againAlarmTimeDialog.position)
            wakeViewModel.setAgainAlarmTime(viewModel.againAlarmTime.value!!)
            binding.createWakeAgainAlarmSwitch.isChecked = true
        }
    }

    override fun negClick(viewType: Int) {
    }
}