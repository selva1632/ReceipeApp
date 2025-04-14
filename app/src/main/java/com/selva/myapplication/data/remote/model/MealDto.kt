package com.selva.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class MealDto(
    @SerializedName("idMeal") val idMeal: String,
    @SerializedName("strMeal") val mealName: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strMealThumb") val mealUrl: String,
    @SerializedName("strInstruction") val instruction: String,
    @SerializedName("strYoutube") val youtubeUrl: String,
    @SerializedName("strSource") val moreInfo: String
)
