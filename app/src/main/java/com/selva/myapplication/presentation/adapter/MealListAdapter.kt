package com.selva.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selva.myapplication.databinding.MealListViewHolderBinding
import com.selva.myapplication.domain.model.MealItem
import com.selva.myapplication.presentation.listener.OnItemClickListener

class MealListAdapter(
    private val eventListener: OnItemClickListener
) : RecyclerView.Adapter<MealListViewHolder>() {

    private val itemList = mutableListOf<MealItem>()

    fun replaceItems(item: List<MealItem>) {
        itemList.clear()
        itemList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        return MealListViewHolder(
            MealListViewHolderBinding.inflate(LayoutInflater.from(parent.context), null, false)
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        holder.bind(itemList[position], eventListener)
    }
}