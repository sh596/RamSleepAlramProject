package com.example.alarmproject.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R

class CreateAlarmActivity : AppCompatActivity() {

    private val fragmentManager : FragmentManager by lazy{
        supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_alarm)

        fragmentManager.beginTransaction()
            .replace(R.id.createAlarmFrameLayout , CreateWakeFragment()).commit()

        val viewModel = ViewModelProvider(this)[AlarmViewModel::class.java]
    }

    fun nextFragment (){
        fragmentManager.beginTransaction().replace(R.id.createAlarmFrameLayout, CreateSleepFragment()).commit()
    }

}