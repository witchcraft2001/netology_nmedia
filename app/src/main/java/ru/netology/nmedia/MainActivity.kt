package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.data.entities.Post
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.utils.NumbersFormatter

class MainActivity : AppCompatActivity() {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий",
        authorAvatar = "@sample/posts_avatars",
        published = "21 мая в 18:36",
        text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor." +
                "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
                "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
                "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, " +
                "imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. " +
                "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, " +
                "porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, " +
                "feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet.",
        isFavorite = false,
        favoriteCount = 52,
        commentsCount = 6,
        repostsCount = 998,
        viewsCount = 7312
    )

    private lateinit var binding: ActivityMainBinding
    private val numbersFormatter = NumbersFormatter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            textViewAuthor.text = post.author
            textViewPost.text = post.text
            textViewTime.text = post.published
            textViewViewsCount.text = numbersFormatter.numberToString(post.viewsCount)
        }
        updateFavoriteCount()
        updateSharesCount()
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            imageViewFavorite.setOnClickListener { onFavoriteClicked() }
            imageViewShares.setOnClickListener { onSharesClicked() }
        }
    }

    private fun onSharesClicked() {
        post = post.copy(repostsCount = post.repostsCount + 1)
        updateSharesCount()
    }

    private fun onFavoriteClicked() {
        post = if (post.isFavorite) {
            post.copy(isFavorite = false, favoriteCount = post.favoriteCount - 1)
        } else {
            post.copy(isFavorite = true, favoriteCount = post.favoriteCount + 1)
        }
        updateFavoriteCount()
    }

    private fun updateFavoriteCount() {
        with(binding) {
            textViewFavorite.text = numbersFormatter.numberToString(post.favoriteCount)
            imageViewFavorite.setImageResource(
                if (post.isFavorite) {
                    R.drawable.ic_favorite_24
                } else {
                    R.drawable.ic_favorite_border_24
                }
            )
        }
    }

    private fun updateSharesCount() {
        binding.textViewShares.text = numbersFormatter.numberToString(post.repostsCount)
    }
}