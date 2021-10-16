package com.example.alarmproject.create

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmproject.R
import com.example.alarmproject.databinding.ItemSleepTimeBinding

class CreateSleepAdapter(private val list: MutableList<SleepTimeItem>, private val checkRadioButton:(Int) -> Unit) :
    RecyclerView.Adapter<CreateSleepAdapter.ViewHolder>() {
    private lateinit var binding: ItemSleepTimeBinding

    var checkList = mutableListOf(true, false, false, false)

    class ViewHolder(val binding: ItemSleepTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(isCheck: Boolean) {
            check(isCheck)
        }
        private fun check(isCheck :Boolean){
            binding.itemSleepTimeRadioButton.isChecked = isCheck
            if(isCheck){
                binding.sleepTimeLayout.background = ContextCompat.getDrawable(binding.root.context, R.drawable.background_primary_600_radius_8_line_yellow)
            }else{
                binding.sleepTimeLayout.background = ContextCompat.getDrawable(binding.root.context, R.drawable.background_primary_600_radius_8)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_sleep_time,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.viewModel = list[position]
        holder.onBind(checkList[position])
        binding.itemSleepTimeRadioButton.apply {
            setOnClickListener {
                for (i in checkList.indices) {
                    checkList[i] = i == position
                }
                checkRadioButton(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = list.size

}