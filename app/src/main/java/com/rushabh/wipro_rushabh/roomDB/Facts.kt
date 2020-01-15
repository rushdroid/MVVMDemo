package com.rushabh.wipro_rushabh.roomDB


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FactTable")
data class Facts(
    @PrimaryKey(autoGenerate = true)
    var uId: Int = 0,
    @TypeConverters(RowConverter::class)
    @SerializedName("rows")
    val rows: List<Row> = emptyList(),
    @SerializedName("title")
    val title: String = ""
)