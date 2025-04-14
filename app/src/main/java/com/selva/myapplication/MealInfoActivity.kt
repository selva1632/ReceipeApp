package com.selva.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.selva.myapplication.databinding.ActivityMealInfoBinding
import com.selva.myapplication.presentation.viewmodel.MealInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealInfoBinding
    private val viewmodel: MealInfoViewModel by viewModels()

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealInfoBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val mealId = intent.getStringExtra(MealListActivity.MEAL_ID)
        mealId?.let {
            viewmodel.fetchMealById(mealId)
        }

        viewmodel.mealInfo.observe(this) { meal ->
            binding.bindItem = meal
        }

        val youTubePlayerView = binding.videoPlayer
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = extractVideoId(viewmodel.mealInfo.value?.youtubeUrl ?: "") ?: ""
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        with(binding) {
            moreInfo.setOnClickListener {
                viewmodel.mealInfo.value?.let {
                    Intent(Intent.ACTION_VIEW, Uri.parse(it.moreInfo)).also { intent ->
                        startActivity(intent)
                    }
                }
            }
        }
    }

    fun extractVideoId(youtubeUrl: String): String? {
        val uri = Uri.parse(youtubeUrl)

        return when {
            youtubeUrl.contains("youtu.be") -> {
                uri.lastPathSegment
            }

            youtubeUrl.contains("watch") -> {
                uri.getQueryParameter("v")
            }

            youtubeUrl.contains("embed") -> {
                uri.lastPathSegment
            }

            else -> ""
        }
    }
}