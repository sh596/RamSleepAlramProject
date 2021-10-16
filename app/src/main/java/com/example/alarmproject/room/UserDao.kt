package com.example.alarmproject.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM mainTable")
    fun getAll() : List<User>
    @Insert
    fun setAlarm(user: User)
    @Query("SELECT * FROM mainTable WHERE isMain")
    fun getMainAlarm(): List<User>
}