package com.example.alarmproject.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmproject.utils.Constant

class CreateWakeViewModel() : ViewModel() {
    var againAlarmTime = MutableLiveData<String>()
    var vibration = MutableLiveData<String>()

    fun setVibration(code: Int){
        when (code) {
            Constant.VIBRATION_1 -> vibration.value = "기본"
            Constant.VIBRATION_2 -> vibration.value = "짧은"
            Constant.VIBRATION_3 -> vibration.value = "긴"
            Constant.VIBRATION_4 -> vibration.value = "시끄러운"
        }
    }

    fun setAgainAlarmTime(code: Int){
        if (code == 0){
            againAlarmTime.value = "사용 안함"
        }else{
            againAlarmTime.value = "${code}분"
        }
    }
}

