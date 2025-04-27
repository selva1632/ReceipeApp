package com.selva.myapplication.data.remote.api

import com.selva.myapplication.data.remote.model.CategoryResponse
import com.selva.myapplication.data.remote.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    suspend fun fetchCategories(): Response<CategoryResponse>

    @GET("search.php")
    suspend fun fetchMealsByCategory(
        @Query("s") mealName: String
    ): Response<MealResponse>

    @GET("lookup.php")
    suspend fun fetchMealsById(@Query("i") id: String): Response<MealResponse>

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}