package com.example.alarmproject.main

import androidx.lifecycle.MutableLiveData

data class AlarmDetailSetting (
    val vibration: Int,
    val ringtone: Int,
    val againAlarm: Boolean,
    val earlyAlarm: Boolean
    )