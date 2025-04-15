package com.selva.myapplication.presentation.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selva.myapplication.domain.model.FoodCategoryItem
import com.selva.myapplication.domain.model.MealItem

@BindingAdapter("bind:foodCategory")
fun setMyCategories(recyclerView: RecyclerView, items: List<FoodCategoryItem>) {
    (recyclerView.adapter as FoodCategoryAdapter).replaceItem(items)
}

@BindingAdapter("bind:meals")
fun setMeals(recyclerView: RecyclerView, items: List<MealItem>) {
    (recyclerView.adapter as MealListAdapter).replaceItems(items)
}