package com.example.alarmproject.create

class SleepTimeItem(time : Int){
    private val time = changeAbsoluteValue(time)

    val sleepTimeText = String.format("${set12Hour()}:%02d", this.time%60)
    val sleepTimeAMPM = setAmPm()

    private fun setAmPm() : String {
        return if (time/60 > 12) {"오후"} else {"오전"}
    }

    private fun set12Hour(): String {
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