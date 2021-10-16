package com.example.alarmproject.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel() {
    val wakeTime = MutableLiveData<String>()
    val sleepTime = MutableLiveData<String>()
    val sleepAbleTime = MutableLiveData<String>()

}

