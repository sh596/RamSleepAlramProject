package com.example.alarmproject.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmproject.utils.Constant

class AlarmViewModel() : ViewModel() {
    val wakeUpTime = MutableLiveData<Int>()
    var vibration = MutableLiveData<Int>()
    var againAlarmTime = MutableLiveData<Int>()
    val earlyAlarmTime = MutableLiveData<Int>()
    val sleepTime = MutableLiveData<Int>()

    init {
        wakeUpTime.value = 0
        vibration.value = 101
        againAlarmTime.value = 0
    }

    fun setVibration(position: Int){
        when (position) {
            0 -> vibration.value = Constant.VIBRATION_1
            1 -> vibration.value = Constant.VIBRATION_2
            2 -> vibration.value = Constant.VIBRATION_3
            3 -> vibration.value = Constant.VIBRATION_4
        }
    }

    fun setAgainAlarmTime(position: Int){
        when (position) {
            0 -> againAlarmTime.value = Constant.AGAIN_ALARM_TIME_1
            1 -> againAlarmTime.value = Constant.AGAIN_ALARM_TIME_2
            2 -> againAlarmTime.value = Constant.AGAIN_ALARM_TIME_3
            3 -> againAlarmTime.value = Constant.AGAIN_ALARM_TIME_4
        }
    }
}