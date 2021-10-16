package com.example.alarmproject.main

import androidx.lifecycle.ViewModel

class AlarmItem (id: Int,val isMain: Boolean, wakeTime : Int, sleepTime: Int){
    val mWakeTime = changeAbsoluteValue(wakeTime)
    val mSleepTime = changeAbsoluteValue(sleepTime)
    val wakeTime = "${setAmPm(mWakeTime)} ${set12Hour(mWakeTime)}:${String.format("%02d",mWakeTime%60)}"
    val sleepTime = "${setAmPm(mSleepTime)} ${set12Hour(mSleepTime)}:${String.format("%02d",mSleepTime%60)}"

    private fun setAmPm(time: Int) : String {
        return if (time/60 > 12) {"오후"} else {"오전"}
    }

    private fun set12Hour(time :Int): String {
        return String.format("%02d", when {
            time/60 > 12 -> {
                time/60 - 12
            }
            time/60 == 0 -> {
                12
            }
            else -> {
                time/60
            }
        }
        )
    }

    private fun changeAbsoluteValue(num: Int): Int {
        return if (num < 0) {
            1440 + num
        } else {
            num
        }
    }


}