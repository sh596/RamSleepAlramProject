package com.example.alarmproject.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import com.example.alarmproject.databinding.FragmentCreateWakeBinding
import com.example.alarmproject.dialog.RadioDialog
import com.example.alarmproject.utils.Constant

class CreateWakeFragment : Fragment(){

    private lateinit var binding: FragmentCreateWakeBinding
    private lateinit var vibrationDialog: RadioDialog
    private lateinit var againAlarmTimeDialog: RadioDialog

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[AlarmFragmentViewModel::class.java]
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
        binding.fragment = this
        binding.lifecycleOwner = this

        return binding.root
    }

    fun nextFragment(view: View) {
        viewModel.wakeUpTime.value = wakeViewModel.pickerHour.value!! * 60 + wakeViewModel.pickerMin.value!!
        (activity as CreateAlarmActivity).nextFragment()
    }


    fun checkAgainAlarm(buttonView: CompoundButton, isCheck: Boolean) {
        if (isCheck) {
            if (viewModel.againAlarmTime.value == 0) {
                wakeViewModel.setAgainAlarmTime(Constant.AGAIN_ALARM_TIME_1)
                viewModel.setAgainAlarmTime(1)
            }
        } else {
            wakeViewModel.setAgainAlarmTime(0)
            viewModel.againAlarmTime.value = 0
        }
    }

    fun setVibrationDialog(view: View) {
        vibrationDialog = RadioDialog(
            requireView().context, "진동 설정",
            mutableListOf("기본", "짧은", "긴", "시끄러운"),  vibrationDialogPosClick
        )
        vibrationDialog.show()
    }

    fun setAgainAlarmDialog(view: View) {
        againAlarmTimeDialog = RadioDialog(
            requireView().context, "알람 간격",
            mutableListOf("5분", "10분", "15분", "20분"), againAlarmDialogPosClick
        )
        againAlarmTimeDialog.show()
    }

    fun finishFragment(view: View) {
        (activity as CreateAlarmActivity).finish()
    }

    private val vibrationDialogPosClick  = {position : Int ->
            viewModel.setVibration(position)
            wakeViewModel.setVibration(viewModel.vibration.value!!)
    }

    private val againAlarmDialogPosClick = {position: Int ->
        viewModel.setAgainAlarmTime(position)
        wakeViewModel.setAgainAlarmTime(viewModel.againAlarmTime.value!!)
        binding.createWakeAgainAlarmSwitch.isChecked = true
    }
}