package com.rushabh.wipro_rushabh.roomDB


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity
data class Row(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("imageHref")
    val imageHref: String= "",
    @SerializedName("title")
    val title: String = ""
)