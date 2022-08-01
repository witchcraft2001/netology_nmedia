package ru.netology.nmedia.ui.main

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.data.repos.PostRepository
import ru.netology.nmedia.data.repos.PostRepositoryImpl

class MainViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()
}