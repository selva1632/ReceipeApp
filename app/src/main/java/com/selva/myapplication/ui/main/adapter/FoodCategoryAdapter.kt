package com.selva.myapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selva.myapplication.databinding.MealCategoryViewholderBinding
import com.selva.myapplication.domain.model.FoodCategoryItem
import com.selva.myapplication.ui.OnItemClickListener

class FoodCategoryAdapter(
    private val eventListener: OnItemClickListener
) : RecyclerView.Adapter<FoodCategoryViewHolder>() {

    private val itemList = mutableListOf<FoodCategoryItem>()

    fun replaceItem(items: List<FoodCategoryItem>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        return FoodCategoryViewHolder(
            MealCategoryViewholderBinding.inflate(
                LayoutInflater.from(parent.context), null, false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        holder.bind(itemList[position], eventListener)
    }
}