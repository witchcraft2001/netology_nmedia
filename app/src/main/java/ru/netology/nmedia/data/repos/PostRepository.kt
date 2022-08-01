package ru.netology.nmedia.data.repos

import androidx.lifecycle.LiveData
import ru.netology.nmedia.data.entities.Post

interface PostRepository {
    fun get() : LiveData<Post>
    fun like()
    fun share()
}