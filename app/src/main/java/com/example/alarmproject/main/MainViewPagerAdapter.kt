package com.example.alarmproject.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(activity: AppCompatActivity, private val fragment: List<Fragment>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = fragment.size
    override fun createFragment(position: Int): Fragment = fragment[position]
}