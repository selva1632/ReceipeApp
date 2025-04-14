package com.selva.myapplication.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selva.myapplication.domain.model.FoodCategoryItem
import com.selva.myapplication.utils.Result
import com.selva.myapplication.domain.repository.MealRepository
import com.selva.myapplication.utils.toFoodCategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCategoryViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _foodCategory = MutableLiveData<List<FoodCategoryItem>>()
    val foodCategory: LiveData<List<FoodCategoryItem>>
        get() = _foodCategory

    fun fetchMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.fetchMeals()) {
                is Result.Success -> {
                        result.data?.let {
                        _foodCategory.postValue(
                            it.map { dto -> dto.toFoodCategoryItem() }
                        )
                    }
                }

                is Result.Error -> {
                    val error = result.message
                    Log.i(TAG, error)
                }
            }
        }
    }

    companion object {
        const val TAG = "MealViewModel"
    }
}