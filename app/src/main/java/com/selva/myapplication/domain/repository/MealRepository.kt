package com.selva.myapplication.domain.repository

import com.selva.myapplication.baseutil.network.Result
import com.selva.myapplication.data.remote.model.FoodCategoryDto
import com.selva.myapplication.data.remote.model.MealDto

interface MealRepository {
    suspend fun fetchMeals(): Result<List<FoodCategoryDto>>
    suspend fun fetchMealsByCategoryName(categoryName: String): Result<List<MealDto>>
    suspend fun fetchMealById(id: String): Result<MealDto>
}