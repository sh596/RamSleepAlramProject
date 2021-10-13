package com.example.alarmproject.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmproject.utils.Constant

class CreateSleepViewModel() : ViewModel() {
    var wakeTime = MutableLiveData<String>()
    var sleepTime = MutableLiveData<String>()
    var earlyAlarmTime = MutableLiveData<String>()

    init {
        wakeTime.value = "오전 00:00"
        sleepTime.value = "--시간 --분"
        earlyAlarmTime.value = "사용 안함"
    }

    fun setWakeTime(time: Int){
        val hour = if((time/100) > 12) {(time/100)-12} else {time/100}
        wakeTime.value = "${hour}:${time%100}"
    }

    fun setSleepTime(wakeTime: Int, sleepTime: Int){
        val time = ((wakeTime/100*60) + wakeTime%100) - ((sleepTime/100*60) + sleepTime%100)
        earlyAlarmTime.value = "${time/100} 시간${time%100}분"
    }

    fun setAgainAlarmTime(code: Int){
        if (code == 0){
            earlyAlarmTime.value = "사용 안함"
        }else{
            earlyAlarmTime.value = "${code}분"
        }
    }
}

