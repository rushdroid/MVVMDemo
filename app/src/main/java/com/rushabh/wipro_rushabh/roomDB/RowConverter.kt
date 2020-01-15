package com.rushabh.wipro_rushabh.roomDB

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object RowConverter {
    @JvmStatic
    @TypeConverter
    fun toList(data: String?): List<Row> {
        if (data == null) {
            return emptyList()
        }
        val listType =
            object : TypeToken<List<Row?>?>() {}.type
        return Gson().fromJson(data, listType)
    }

    @JvmStatic
    @TypeConverter
    fun toGson(builtByList: List<Row?>?): String {
        return Gson().toJson(builtByList)
    }
}