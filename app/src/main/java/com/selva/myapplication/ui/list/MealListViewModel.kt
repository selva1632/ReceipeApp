package com.selva.myapplication.ui.list

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
class MealListViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _toastLiveData = MutableLiveData<String>()
    val toastLiveData: LiveData<String>
        get() = _toastLiveData

    private val _mealList = MutableLiveData<List<MealItem>>(emptyList())
    val mealList: LiveData<List<MealItem>>
        get() = _mealList

    fun fetchMealsByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val response = repository.fetchMealsByCategoryName(name)) {
                is Result.Success -> {
                    response.data?.let {
                        _mealList.postValue(
                            it.map { dto -> dto.toMealItem() }
                        )
                    } ?: _toastLiveData.postValue("no recipe found")
                }

                is Result.Error -> {
                    Log.i(TAG, response.message)
                    _toastLiveData.postValue(response.message)
                }
            }
        }
    }

    companion object {
        const val TAG = "MealListViewModel"
    }

}