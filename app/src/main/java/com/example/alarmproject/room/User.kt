package com.example.alarmproject.room

import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mainTable")
data class User(
    val mainWakeUpTime: Int,
    val mainSleepTime: Int,
    val mainSleepAbleTime: Int,
    val vibrationType: Int,
    val againAlarmTime: Int,
    val earlyAlarmTime: Int,
    val isMain: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
