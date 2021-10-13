package com.example.alarmproject.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BannerViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BannerViewModel::class.java)){
            BannerViewModel(context) as T
        }else{
            throw IllegalArgumentException()
        }
    }
}