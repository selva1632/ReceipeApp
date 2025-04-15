package com.selva.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.selva.myapplication.data.MealRepositoryImpl
import com.selva.myapplication.databinding.ActivityMealListBinding
import com.selva.myapplication.domain.model.MealItem
import com.selva.myapplication.presentation.adapter.MealListAdapter
import com.selva.myapplication.presentation.listener.OnItemClickListener
import com.selva.myapplication.presentation.viewmodel.MealListViewModel
import com.selva.myapplication.presentation.viewmodel.factory.MealListViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealListBinding

    private val viewmodel by viewModels<MealListViewModel> {
        MealListViewModelFactory(
            MealRepositoryImpl(RetrofitInstance.apiService)
        )
    }

    private val eventListener by lazy {
        object : OnItemClickListener {
            override fun onClick(item: Any) {
                when (item) {
                    is MealItem -> {
                        startInfoActivity(item)
                    }
                    else -> {}
                }
            }
        }
    }

    private val adapter by lazy {
        MealListAdapter(eventListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealListBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val categoryName = intent.getStringExtra(MainActivity.CATEGORY_NAME) ?: ""

        with(binding) {
            lifecycleOwner = this@MealListActivity
            vm = viewmodel
            mealsRecyclerView.adapter = adapter
        }

        viewmodel.fetchMealsByName(categoryName)
        initObserver()
    }

    private fun initObserver() {
        viewmodel.toastLiveData.observe(this) {
            if (it.isNotEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                lifecycleScope.launch {
                    delay(3_000)
                    finish()
                }
            }
        }
    }

    private fun startInfoActivity(item: MealItem) {
        Intent(this, MealInfoActivity::class.java).apply {
            putExtra(MEAL_ID, item.idMeal)
        }.also {
            startActivity(it)
        }
    }

    companion object {
        const val MEAL_ID = "meal_id"
    }
}