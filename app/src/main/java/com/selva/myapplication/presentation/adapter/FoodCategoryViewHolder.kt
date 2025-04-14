package com.selva.myapplication.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selva.myapplication.databinding.MealCategoryViewholderBinding
import com.selva.myapplication.domain.model.FoodCategoryItem
import com.selva.myapplication.presentation.listener.onItemClickListener

class FoodCategoryViewHolder(
    open val binding: MealCategoryViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FoodCategoryItem, evenListener: onItemClickListener) {
        with(binding) {
            bindItem = item
            Glide.with(mealImage.context)
                .load(item.imageUrl)
                .into(mealImage)

            root.setOnClickListener {
                evenListener.onClick(item)
            }
        }
    }
}