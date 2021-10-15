package com.example.alarmproject.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmproject.R
import com.example.alarmproject.databinding.ItemRadioBinding

class RadioAdapter(list: MutableList<String>, checkList: MutableList<Boolean>) : RecyclerView.Adapter<RadioAdapter.ViewHolder>() {

    private lateinit var binding: ItemRadioBinding
    private val list : MutableList<String> = list
    var checkList : MutableList<Boolean> = checkList

    class ViewHolder(val binding: ItemRadioBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(option: String, isCheck: Boolean){
            binding.radioOptionText.text = option
            binding.radioDialgRadioButton.isChecked = isCheck
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_radio,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position], checkList[position])
        binding.radioDialgRadioButton.apply {
            setOnClickListener{
                for(i in checkList.indices){
                    checkList[i] = i == position
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = list.size
}