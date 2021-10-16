package com.example.alarmproject.create

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import com.example.alarmproject.databinding.FragmentCreateSleepBinding
import com.example.alarmproject.dialog.RadioDialog
import com.example.alarmproject.room.AppDatabase
import com.example.alarmproject.room.User
import com.example.alarmproject.utils.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CreateSleepFragment : Fragment() {
    private lateinit var binding: FragmentCreateSleepBinding

    private val sleepViewModel by lazy {
        ViewModelProvider(this)[CreateSleepViewModel::class.java]
    }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(AlarmFragmentViewModel::class.java)
    }

    private lateinit var adapter: CreateSleepAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_sleep, container, false)
        binding.viewModel = sleepViewModel
        binding.fragment = this
        binding.lifecycleOwner = this

        val view = binding.root
        Log.d(TAG, "onCreateView: ${viewModel.wakeUpTime.value}")
        sleepViewModel.setWakeTime(viewModel.wakeUpTime.value!!)
        setRecyclerView()

        return view
    }

    fun setEarlyAlarmDialog(view: View) {
        val earlyAlarmDialog = RadioDialog(
            view.context,
            "미리 알림",
            mutableListOf("10분", "20분", "30분"),
            earlyAlarmPosClick
        )
        earlyAlarmDialog.show()
    }

    private fun setRecyclerView() {
        sleepViewModel.setItemList(viewModel.wakeUpTime.value!!)
        binding.createSleepRecyclerView.adapter = CreateSleepAdapter(sleepViewModel.itemList.value!!, checkRadioButton)
    }

    fun createAlarm(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getInstance(binding.root.context)!!.userDao().setAlarm(
                User(
                    viewModel.wakeUpTime.value!!,
                    viewModel.sleepTime.value!!,
                    viewModel.sleepAbleTime.value!!,
                    viewModel.vibration.value!!,
                    viewModel.againAlarmTime.value!!,
                    viewModel.earlyAlarmTime.value!!,
                    false
                )
            )
        }
        Log.d(TAG, "createAlarm: "+viewModel.sleepTime.value!!)
        (activity as CreateAlarmActivity).finish()
    }

    fun checkEarlyAlarm(buttonView: CompoundButton, isCheck: Boolean) {
        if (isCheck) {
            if (viewModel.earlyAlarmTime.value == 0) {
                sleepViewModel.setEarlyAlarmTime(Constant.EARLY_ALARM_TIME_1)
                viewModel.setEarlyAlarmTime(0)
            }
        } else {
            sleepViewModel.setEarlyAlarmTime(0)
            viewModel.earlyAlarmTime.value = 0
        }
    }

    private val earlyAlarmPosClick = { position: Int ->
        viewModel.setEarlyAlarmTime(position)
        sleepViewModel.earlyAlarmChecked.value = true
        sleepViewModel.setEarlyAlarmTime(viewModel.earlyAlarmTime.value!!)
    }

    private val checkRadioButton = { position: Int ->
        sleepViewModel.setSleepTime(position)
        viewModel.setSleepTime(position)
    }


    fun finishFragment(view :View){
        (activity as CreateAlarmActivity).backFragment()
    }


}