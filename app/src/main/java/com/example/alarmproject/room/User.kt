package com.example.alarmproject.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class DayList(
    val dat: Boolean
)

@Entity(tableName = "mainTable")
data class User(
    @PrimaryKey val id: Int,
    val wakeUpTime: Int,
    val sleepTime: Int,
    @Embedded val dayList: DayList)
