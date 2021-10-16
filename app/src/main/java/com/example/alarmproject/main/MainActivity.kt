package com.example.alarmproject.main

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.alarmproject.R
import com.example.alarmproject.alarm.AlarmReceiver
import com.example.alarmproject.create.CreateAlarmActivity
import com.example.alarmproject.databinding.ActivityMainBinding
import com.example.alarmproject.dialog.CommonDialog
import com.example.alarmproject.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var setAlarmDialog: CommonDialog
    private lateinit var adapter: MainAdapter
    private var id : Int? = null


    private val bannerViewModel: BannerViewModel by lazy {
        ViewModelProvider(this, BannerViewModelFactory(this))[BannerViewModel::class.java]
    }
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()

        setRecyclerView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.bannerViewModel = bannerViewModel
        binding.mainViewModel = mainViewModel
        binding.activity = this

        getMainAlarm()
    }

    fun createAlarm(view: View){
        startActivity(Intent(this, CreateAlarmActivity::class.java))
    }

    private fun setRecyclerView(){
        CoroutineScope(Dispatchers.Main).launch{
            CoroutineScope(Dispatchers.IO).async {
                adapter = MainAdapter(AppDatabase.getInstance(applicationContext)!!.userDao().getAll(),setAlarm)
            }.await()
            binding.mainRecyclerView.adapter = adapter
        }
    }

    private val setAlarm = {id :Int ->
        this.id = id
        setAlarmDialog = CommonDialog(this,"알람 설정", "알람을 설정하시겠습니까?",){posClick}
        setAlarmDialog.show()
    }

    private val posClick = {id :Int ->
        CoroutineScope(Dispatchers.IO).launch {
        var alarmManager = this@MainActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(this@MainActivity, AlarmReceiver::class.java)
        var pendingIntent = PendingIntent.getBroadcast(this@MainActivity, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        alarmManager.cancel(pendingIntent)

        val list = AppDatabase.getInstance(this@MainActivity)!!.userDao().getMainAlarm()
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, list[0].mainSleepTime/60)
        cal.set(Calendar.MINUTE, list[0].mainSleepTime%60)

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.timeInMillis, pendingIntent)

        }
    }

    private fun getMainAlarm(){
        CoroutineScope(Dispatchers.IO).launch {
            if(!AppDatabase.getInstance(applicationContext)!!.userDao().getMainAlarm().isEmpty()){
                mainViewModel.wakeTime.value =
                    AppDatabase.getInstance(applicationContext)!!.userDao().getMainAlarm()[0].mainWakeUpTime.toString()

                mainViewModel.sleepTime.value =
                    AppDatabase.getInstance(applicationContext)!!.userDao().getMainAlarm()[0].mainSleepTime.toString()

                mainViewModel.sleepAbleTime.value =
                    AppDatabase.getInstance(applicationContext)!!.userDao().getMainAlarm()[0].mainSleepAbleTime.toString()
            }
        }

    }

}