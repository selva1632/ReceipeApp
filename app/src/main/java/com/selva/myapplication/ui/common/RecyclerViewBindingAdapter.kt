package com.selva.myapplication.ui.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selva.myapplication.domain.model.FoodCategoryItem
import com.selva.myapplication.domain.model.MealItem
import com.selva.myapplication.ui.list.adapter.MealListAdapter
import com.selva.myapplication.ui.main.adapter.FoodCategoryAdapter

@BindingAdapter("bind:foodCategory")
fun setMyCategories(recyclerView: RecyclerView, items: List<FoodCategoryItem>) {
    (recyclerView.adapter as FoodCategoryAdapter).replaceItem(items)
}

@BindingAdapter("bind:meals")
fun setMeals(recyclerView: RecyclerView, items: List<MealItem>) {
    (recyclerView.adapter as MealListAdapter).replaceItems(items)
}