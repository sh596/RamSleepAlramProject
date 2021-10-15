package com.example.alarmproject.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.alarmproject.R
import com.example.alarmproject.databinding.ActivityRadioBinding

class RadioDialog(
    context: Context,
    private val title: String,
    private val list: MutableList<String>,
    private val posClick:(Int) -> Unit
) : Dialog(context) {

    var position = 0
    private lateinit var binding: ActivityRadioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.activity_radio,
            null,
            false
        );
        setContentView(binding.root)
        showDialog()
    }

    fun showDialog() {

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.radioDialogText.text = title

        val adapter = RadioAdapter(list, setCheckList())
        binding.radioDialogRecyclerView.adapter = adapter

        binding.positiveButton.setOnClickListener {
            for (i in list.indices) {
                if (adapter.checkList[i]) {
                    position = i
                    break
                }
            }
            posClick(position)
            dismiss()
        }

        binding.negativeButton.setOnClickListener {
            dismiss()
        }
    }

    fun setCheckList(): MutableList<Boolean> {
        val checkList = mutableListOf<Boolean>()
        repeat(list.size) {
            checkList.add(false)
        }
        checkList[0] = true

        return checkList
    }
}