package com.example.alarmproject.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import com.example.alarmproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel: BannerViewModel by lazy {
        ViewModelProvider(this, BannerViewModelFactory(this)).get(BannerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.bannerViewModel = viewModel

        binding.mainAlarmAddButton.setOnClickListener {
            startActivity(Intent(this,CreateAlarmActivity::class.java))
        }
    }


}