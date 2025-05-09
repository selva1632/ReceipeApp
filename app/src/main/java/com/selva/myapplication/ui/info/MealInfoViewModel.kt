package com.selva.myapplication.ui.info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selva.myapplication.domain.model.MealItem
import com.selva.myapplication.domain.repository.MealRepository
import com.selva.myapplication.baseutil.network.Result
import com.selva.myapplication.data.remote.mapper.toMealItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealInfoViewModel @Inject constructor(
    private val repository: MealRepository
): ViewModel() {

    private val _mealInfo = MutableLiveData<MealItem>()
    val mealInfo: LiveData<MealItem>
        get() = _mealInfo

    fun fetchMealById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.fetchMealById(id)) {
                is Result.Success -> {
                    result.data?.let {
                        _mealInfo.postValue(it.toMealItem())
                    }
                }

                is Result.Error -> {
                    Log.i(TAG, result.message)
                }
            }
        }
    }

    companion object {
        const val TAG = "MealInfoViewModel"
    }
}