package com.example.alarmproject.dialog

import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.alarmproject.R
import com.example.alarmproject.databinding.DialogCommonBinding

class CommonDialog(
    context: Context,
    private val title: String,
    private val message: String,
    private val posClick:() -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogCommonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_common,
            null,
            false
        );
        setContentView(binding.root)
        showDialog()
    }

    private fun showDialog() {
        Log.d(TAG, "showDialog: 성공")
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.titleText.text = title

        binding.messageText.text = message

        binding.positiveButton.setOnClickListener {
            posClick()
            dismiss()
        }

        binding.negativeButton.setOnClickListener {
            dismiss()
        }
    }
}