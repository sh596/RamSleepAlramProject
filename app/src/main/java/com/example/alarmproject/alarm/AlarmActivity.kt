package com.example.alarmproject.alarm

import android.app.Activity
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.alarmproject.R
import com.example.alarmproject.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding :ActivityAlarmBinding
    private lateinit var ringtone : Ringtone

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alarm)

        binding.viewModel = AlarmViewModel()
        binding.activity = this

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(this,uri)
        ringtone.play()


    }
    fun release(view: View){
        ringtone.stop()
        finish()
    }
}