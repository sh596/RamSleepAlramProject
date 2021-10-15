package com.example.alarmproject.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateSleepViewModel() : ViewModel() {
    val wakeTime = MutableLiveData<String>()
    var sleepTime = MutableLiveData<String>()
    var againAlarmChecked = MutableLiveData<Boolean>()
    var earlyAlarmTime = MutableLiveData<String>()
    var itemList = MutableLiveData<MutableList<SleepTimeItem>>()

    fun setItemList(time: Int){
        itemList.value = mutableListOf(
            SleepTimeItem(time-540),
            SleepTimeItem(time-450),
            SleepTimeItem(time-360),
            SleepTimeItem(time-270)
        )
    }

    fun setWakeTime(time: Int){
        wakeTime.value = String.format("%s %02d:%02d", setAmPm(time/60), change12hour(time/60), time%60)
    }

    fun setSleepTime(wakeTime: Int, sleepTime: Int){
        val time = wakeTime - sleepTime
        earlyAlarmTime.value = "${time/60} 시간${time%60}분"
    }

    fun setAgainAlarmTime(code: Int){
        if (code == 0){
            earlyAlarmTime.value = "사용 안함"
        }else{
            earlyAlarmTime.value = "${code}분"
        }
    }
    private fun change12hour(hour : Int) : Int = if (hour > 12) { hour - 12 }else{hour}

    private fun setAmPm(hour: Int): String = if (hour > 12) {"오후"} else {"오전"}


}

