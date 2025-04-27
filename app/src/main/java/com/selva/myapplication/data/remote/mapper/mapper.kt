package com.selva.myapplication.data.remote.mapper

import com.selva.myapplication.data.remote.model.FoodCategoryDto
import com.selva.myapplication.data.remote.model.MealDto
import com.selva.myapplication.domain.model.FoodCategoryItem
import com.selva.myapplication.domain.model.MealItem

fun FoodCategoryDto.toFoodCategoryItem(): FoodCategoryItem {
    return FoodCategoryItem(
        categoryId = idCategory,
        strCategory = strCategory,
        imageUrl = imageUrl,
        categoryDescription = categoryDescription
    )
}

fun MealDto.toMealItem(): MealItem {
    return MealItem(
        idMeal = idMeal,
        mealName = mealName,
        strCategory = strCategory,
        mealUrl = mealUrl,
        instruction = instruction,
        youtubeUrl = youtubeUrl,
        moreInfo = moreInfo
    )
}