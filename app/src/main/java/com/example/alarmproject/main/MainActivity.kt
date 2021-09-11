package com.example.alarmproject.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.alarmproject.R
import com.example.alarmproject.alarm.AlarmFragment
import com.example.alarmproject.databinding.ActivityMainBinding
import com.example.alarmproject.profile.ProfileFragment
import com.example.alarmproject.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val alarmFragment by lazy { AlarmFragment()}
    private val profileFragment by lazy { ProfileFragment()}
    private val settingFragment by lazy { SettingFragment()}

    private val fragmentList: List<Fragment> = listOf(
        profileFragment, alarmFragment, settingFragment
    )

    private val pagerAdapter: MainViewPagerAdapter by lazy {
        MainViewPagerAdapter(this,fragmentList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewPager()
        initNavigationBar()

    }

    private fun initNavigationBar(){
        binding.bottomBar.run {
            setOnItemSelectedListener {
                val page = when(it.itemId) {
                    R.id.profile -> 0
                    R.id.main -> 1
                    R.id.setting -> 2
                    else -> 1
                }
                if (page != binding.pager.currentItem){
                    binding.pager.currentItem = page
                }
                true
            }
            selectedItemId = R.id.main
        }
    }

    private fun initViewPager() {
        binding.pager.run {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val navigation = when(position) {
                        0 -> R.id.profile
                        1 -> R.id.main
                        2 -> R.id.setting
                        else -> R.id.main
                    }
                    if (binding.bottomBar.selectedItemId != navigation){
                        binding.bottomBar.selectedItemId = navigation
                    }
                }
            })
        }
    }
}