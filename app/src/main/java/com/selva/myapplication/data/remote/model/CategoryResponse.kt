package com.selva.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("categories")
    val category: List<FoodCategoryDto>
)