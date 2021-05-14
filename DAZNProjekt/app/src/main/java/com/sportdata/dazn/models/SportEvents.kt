package com.sportdata.dazn.models
import com.google.gson.annotations.SerializedName
data class SportEvents (

	@SerializedName("id") val id : Int,
	@SerializedName("title") val title : String,
	@SerializedName("subtitle") val subtitle : String,
	@SerializedName("date") val date : String,
	@SerializedName("imageUrl") val imageUrl : String,
	@SerializedName("videoUrl") val videoUrl : String
)