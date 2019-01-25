package com.googry.fcmchat.data.room.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateTypeConverter {

    @TypeConverter
    fun fromStringToDate(time: Long) = Calendar.getInstance().let {
        it.timeInMillis = time
        it.time
    }

    @TypeConverter
    fun fromDateToString(date: Date) = date.time

}