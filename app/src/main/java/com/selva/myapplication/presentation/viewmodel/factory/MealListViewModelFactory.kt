package com.selva.myapplication.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.selva.myapplication.domain.repository.MealRepository
import com.selva.myapplication.presentation.viewmodel.MealListViewModel

class MealListViewModelFactory(private val repository: MealRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealListViewModel(
            repository
        ) as T
    }
}