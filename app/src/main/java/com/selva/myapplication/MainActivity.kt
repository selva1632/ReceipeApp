package com.selva.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.selva.myapplication.presentation.adapter.FoodCategoryAdapter
import com.selva.myapplication.domain.model.FoodCategoryItem
import com.selva.myapplication.presentation.listener.OnItemClickListener
import com.selva.myapplication.databinding.ActivityMainBinding
import com.selva.myapplication.presentation.viewmodel.FoodCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: FoodCategoryViewModel by viewModels()

    private val eventListener by lazy {
        object : OnItemClickListener {
            override fun onClick(item: Any) {
                when (item) {
                    is FoodCategoryItem -> startMealListActivity(item)
                    else -> {}
                }
            }
        }
    }
    private val adapter by lazy {
        FoodCategoryAdapter(eventListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        with(binding) {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            foodCategoryRecyclerView.adapter = adapter
            foodCategoryRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
        }

        viewModel.fetchMeals()
    }

    fun startMealListActivity(item: FoodCategoryItem) {
        Intent(this, MealListActivity::class.java).apply {
            putExtra(CATEGORY_NAME, item.strCategory)
        }.also {
            startActivity(it)
        }
    }

    companion object {
        const val CATEGORY_NAME = "categoryName"
    }
}