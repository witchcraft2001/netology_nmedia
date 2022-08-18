package ru.netology.nmedia.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.utils.NumbersFormatter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private val numbersFormatter = NumbersFormatter()
    private val adapter = PostAdapter(
        numbersFormatter = numbersFormatter,
        onLikeListener = { id ->
            viewModel.like(id)
        },
        onShareListener = { id ->
            viewModel.share(id)
        },
        onRemoveListener = { id ->
            viewModel.remove(id)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.swapAdapter(adapter, false)
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }

    override fun onDestroy() {
        binding.recyclerView.swapAdapter(null, false)
        super.onDestroy()
    }
}