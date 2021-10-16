package com.example.alarmproject.alarm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class AlarmViewModel : ViewModel(){
    val time = MutableLiveData<String>()
    val date = MutableLiveData<String>()

    init {
        Date(System.currentTimeMillis())
        val timeFormat = SimpleDateFormat("HH:mm", Locale("ko", "KR"))
        time.value = timeFormat.format(Date(System.currentTimeMillis()))


        Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("MM월 dd일", Locale("ko", "KR"))
        time.value = dateFormat.format(Date(System.currentTimeMillis()))

    }
}