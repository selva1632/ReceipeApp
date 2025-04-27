package com.selva.myapplication.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selva.myapplication.databinding.MealListViewHolderBinding
import com.selva.myapplication.domain.model.MealItem
import com.selva.myapplication.ui.OnItemClickListener

class MealListViewHolder(
    val binding: MealListViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MealItem, eventListener: OnItemClickListener) {
        with(binding) {
            bindItem = item
            root.setOnClickListener {
                eventListener.onClick(item)
            }

            item.mealUrl?.let {
                Glide.with(mealImage.context)
                    .load(it)
                    .into(mealImage)
            }
        }
    }
}