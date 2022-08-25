package ru.netology.nmedia.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.netology.nmedia.data.entities.Post
import ru.netology.nmedia.data.repos.PostRepository
import ru.netology.nmedia.data.repos.PostRepositoryImpl

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryImpl(application)
    val data = repository.getAll()

    fun like(id: Long) = repository.likeById(id)
    fun share(id: Long) = repository.shareById(id)

    fun remove(id: Long) {
        repository.removeById(id)
    }

    fun save(post: Post) {
        repository.save(post)
    }
}
