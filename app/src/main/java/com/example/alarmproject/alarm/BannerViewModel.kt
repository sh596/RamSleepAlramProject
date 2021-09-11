package com.example.alarmproject.alarm

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import java.util.*

class BannerViewModel(val context: Context) : ViewModel() {
    val bannerText = MutableLiveData<String>()
    var mainWakeUpTime = MutableLiveData<String>()

    init {
        setBannerText()
    }
    fun setBannerText(){
        val randomValue = Random().nextInt(3)
        when(randomValue+1) {
            1 -> bannerText.value = context.getString(R.string.banner_1)
            2 -> bannerText.value = context.getString(R.string.banner_2)
            3 -> bannerText.value = context.getString(R.string.banner_3)
            else -> bannerText.value = context.getString(R.string.banner_1)
        }
    }


}