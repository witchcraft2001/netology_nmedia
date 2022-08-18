package ru.netology.nmedia.ui.main

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.data.repos.PostRepository
import ru.netology.nmedia.data.repos.PostRepositoryImpl

class MainViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryImpl()
    val data = repository.getAll()
    fun like(id: Long) = repository.likeById(id)
    fun share(id: Long) = repository.shareById(id)
    fun remove(id: Long) = repository.removeById(id)
}