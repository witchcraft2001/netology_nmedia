package ru.netology.nmedia.data.repos

import androidx.lifecycle.LiveData
import ru.netology.nmedia.data.entities.Post

interface PostRepository {
    fun getAll() : LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
}