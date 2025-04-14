package com.selva.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals")
    val meals: List<MealDto>
)
