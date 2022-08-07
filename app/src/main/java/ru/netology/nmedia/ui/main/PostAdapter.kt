package ru.netology.nmedia.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.data.entities.Post
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.utils.NumbersFormatter

class PostAdapter(
    private val numbersFormatter: NumbersFormatter,
    private val onLikeListener: AdapterClickListener,
    private val onShareListener: AdapterClickListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffUtilCallback) {

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            CardPostBinding.inflate(layoutInflater, parent, false),
            numbersFormatter,
            onLikeListener,
            onShareListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    private object PostDiffUtilCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
    }
}
