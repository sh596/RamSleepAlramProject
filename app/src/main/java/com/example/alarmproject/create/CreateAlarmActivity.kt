package com.example.alarmproject.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
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

        val viewModel = ViewModelProvider(this)[AlarmFragmentViewModel::class.java]
    }

    fun nextFragment (){
        fragmentManager.beginTransaction().replace(R.id.createAlarmFrameLayout, CreateSleepFragment()).commit()
    }

    fun backFragment (){
        fragmentManager.beginTransaction().replace(R.id.createAlarmFrameLayout, CreateWakeFragment()).commit()
    }

}