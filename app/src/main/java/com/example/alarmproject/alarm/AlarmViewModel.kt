package com.example.alarmproject.alarm

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import java.util.*

class AlarmViewModel() : ViewModel() {
    val bannerText = MutableLiveData<String>()
    val mainWakeUpTime = MutableLiveData<String>()
}