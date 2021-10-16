package com.example.alarmproject.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmproject.utils.Constant

class AlarmFragmentViewModel() : ViewModel() {
    val wakeUpTime = MutableLiveData<Int>()
    var vibration = MutableLiveData<Int>()
    var againAlarmTime = MutableLiveData<Int>()
    val earlyAlarmTime = MutableLiveData<Int>()
    val sleepTime = MutableLiveData<Int>()
    val sleepAbleTime = MutableLiveData<Int>()

    init {
        wakeUpTime.value = 0
        setSleepTime(0)
        setSleepAbleTime(0)
        vibration.value = 101
        againAlarmTime.value = 0
        earlyAlarmTime.value = 0

    }

    fun setVibration(position: Int) {
        when (position) {
            0 -> vibration.value = Constant.VIBRATION_1
            1 -> vibration.value = Constant.VIBRATION_2
            2 -> vibration.value = Constant.VIBRATION_3
            3 -> vibration.value = Constant.VIBRATION_4
        }
    }

    fun setAgainAlarmTime(position: Int) {
        when (position) {
            0 -> againAlarmTime.value = Constant.AGAIN_ALARM_TIME_1
            1 -> againAlarmTime.value = Constant.AGAIN_ALARM_TIME_2
            2 -> againAlarmTime.value = Constant.AGAIN_ALARM_TIME_3
            3 -> againAlarmTime.value = Constant.AGAIN_ALARM_TIME_4
        }
    }

    fun setEarlyAlarmTime(position: Int) {
        when (position) {
            0 -> earlyAlarmTime.value = Constant.EARLY_ALARM_TIME_1
            1 -> earlyAlarmTime.value = Constant.EARLY_ALARM_TIME_2
            2 -> earlyAlarmTime.value = Constant.EARLY_ALARM_TIME_3
        }
    }

    fun setSleepTime(position: Int) {
        sleepTime.value = when (position) {
            0 -> wakeUpTime.value!! - 540
            1 -> wakeUpTime.value!! - 450
            2 -> wakeUpTime.value!! - 360
            3 -> wakeUpTime.value!! - 270
            else -> wakeUpTime.value!! - 540
        }
    }

    fun setSleepAbleTime(position: Int) {
        sleepAbleTime.value = 270 + 90 * position
    }

    private fun changeAbsoluteValue(num: Int): Int {
        return if (num < 0) {
            1440 + num
        } else {
            num
        }
    }




}