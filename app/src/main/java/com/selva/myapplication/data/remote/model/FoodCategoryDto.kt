package com.selva.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class FoodCategoryDto(
    @SerializedName("idCategory") val idCategory: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryThumb") val imageUrl: String,
    @SerializedName("strCategoryDescription") val categoryDescription: String
)
