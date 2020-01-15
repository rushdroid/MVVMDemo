package com.rushabh.wipro_rushabh.roomDB


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity
data class Row(
    @SerializedName("description")
    var description: String = "",
    @SerializedName("imageHref")
    var imageHref: String= "",
    @SerializedName("title")
    var title: String = ""
)