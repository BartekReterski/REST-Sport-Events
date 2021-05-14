package com.sportdata.dazn.models

import com.google.gson.annotations.SerializedName

data class ScheduleEvents(

    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("subtitle") val subtitle : String,
    @SerializedName("date") val date : String,
    @SerializedName("imageUrl") val imageUrl : String
)