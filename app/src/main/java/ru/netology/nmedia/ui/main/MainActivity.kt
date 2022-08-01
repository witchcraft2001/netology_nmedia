package ru.netology.nmedia.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.utils.NumbersFormatter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private val numbersFormatter = NumbersFormatter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.data.observe(this) { post ->
            with(binding) {
                textViewAuthor.text = post.author
                textViewPost.text = post.text
                textViewTime.text = post.published
                textViewViewsCount.text = numbersFormatter.numberToString(post.viewsCount)
                textViewFavorite.text = numbersFormatter.numberToString(post.likesCount)
                imageViewFavorite.setImageResource(
                    if (post.isLiked) {
                        R.drawable.ic_favorite_24
                    } else {
                        R.drawable.ic_favorite_border_24
                    }
                )
                textViewShares.text = numbersFormatter.numberToString(post.repostsCount)
            }
        }
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            imageViewFavorite.setOnClickListener { viewModel.like() }
            imageViewShares.setOnClickListener { viewModel.share() }
        }
    }

}