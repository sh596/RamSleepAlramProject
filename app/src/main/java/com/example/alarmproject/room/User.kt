package com.example.alarmproject.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mainTable")
data class User(
    @PrimaryKey val id: Int,
    val mainWakeUpTime: Int,
    val mainSleepTime: Int,
)
