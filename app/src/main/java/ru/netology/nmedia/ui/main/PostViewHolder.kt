package ru.netology.nmedia.ui.main

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.data.entities.Post
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.utils.NumbersFormatter

class PostViewHolder(
    private val binding: CardPostBinding,
    private val numbersFormatter: NumbersFormatter,
    private val onLikeListener: AdapterClickListener,
    private val onShareListener: AdapterClickListener,
    private val onRemoveListener: AdapterClickListener,
    private val onEditListener: PostAdapterClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
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
            imageViewFavorite.setOnClickListener { onLikeListener(post.id) }
            imageViewShares.setOnClickListener { onShareListener(post.id) }
            imageButtonMore.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onRemoveListener(post.id)
                                true
                            }
                            R.id.edit -> {
                                onEditListener(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}
