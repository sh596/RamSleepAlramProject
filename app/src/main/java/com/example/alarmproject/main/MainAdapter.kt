package com.example.alarmproject.main

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmproject.R
import com.example.alarmproject.databinding.ItemAlarmBinding
import com.example.alarmproject.room.User

class MainAdapter(private val list: List<User>, private val setAlarm:(Int)-> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private lateinit var binding: ItemAlarmBinding

    class ViewHolder(private val binding: ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(isMain:Boolean){
            if(isMain){
                binding.itemLayout.background = ContextCompat.getDrawable(binding.root.context, R.drawable.background_primary_600_radius_8_line_red)
            }else{
                binding.itemLayout.background = ContextCompat.getDrawable(binding.root.context, R.drawable.background_primary_600_radius_8)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_alarm,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        binding.viewModel = AlarmItem(list[position].id,list[position].isMain ,list[position].mainWakeUpTime, list[position].mainSleepTime)
        holder.onBind(list[position].isMain)
        binding.root.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: 성공")
            setAlarm(list[position].id)
        }
    }

    override fun getItemCount(): Int = list.size

}