package com.selva.myapplication.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.selva.myapplication.presentation.viewmodel.FoodCategoryViewModel
import com.selva.myapplication.domain.repository.MealRepository

class FoodCategoryViewModelFactory(
    private val repository: MealRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodCategoryViewModel(
            repository
        ) as T
    }
}