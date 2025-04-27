package com.selva.myapplication.data.remote.repository

import com.selva.myapplication.data.remote.api.ApiService
import com.selva.myapplication.data.remote.model.FoodCategoryDto
import com.selva.myapplication.data.remote.model.MealDto
import com.selva.myapplication.domain.repository.MealRepository
import com.selva.myapplication.utils.network.Result
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(private val api: ApiService) : MealRepository {
    override suspend fun fetchMeals(): Result<List<FoodCategoryDto>> {
        val response = api.fetchCategories()
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.Success(it.category)
            } ?: Result.Error("Empty body")
        } else {
            Result.Error(response.message())
        }
    }

    override suspend fun fetchMealsByCategoryName(categoryName: String): Result<List<MealDto>> {
        val response = api.fetchMealsByCategory(categoryName)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.Success(it.meals)
            } ?: Result.Error("Empty body")
        } else {
            Result.Error(response.message())
        }
    }

    override suspend fun fetchMealById(id: String): Result<MealDto> {
        val response = api.fetchMealsById(id)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.Success(it.meals.first())
            } ?: Result.Error(response.message())
        } else {
            Result.Error(response.message())
        }
    }
}