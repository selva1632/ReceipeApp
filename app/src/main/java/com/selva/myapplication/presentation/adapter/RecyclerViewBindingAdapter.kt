package com.selva.myapplication.presentation.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.selva.myapplication.domain.model.FoodCategoryItem

@BindingAdapter("bind:foodCategory")
fun setMyCategories(recyclerView: RecyclerView, items: List<FoodCategoryItem>) {
    (recyclerView.adapter as FoodCategoryAdapter).replaceItem(items)
}