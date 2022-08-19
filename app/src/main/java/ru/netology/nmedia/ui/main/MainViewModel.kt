package ru.netology.nmedia.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.data.entities.Post
import ru.netology.nmedia.data.repos.PostRepository
import ru.netology.nmedia.data.repos.PostRepositoryImpl

class MainViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryImpl()
    val data = repository.getAll()
    val post = MutableLiveData(Post.getEmpty())
    val sourceMessage = MutableLiveData<String>(null)
    fun like(id: Long) = repository.likeById(id)
    fun share(id: Long) = repository.shareById(id)

    fun remove(id: Long) {
        if (post.value?.id == id) {
            cancelEdit()
        }
        repository.removeById(id)
    }

    fun save() {
        post.value?.let {
            repository.save(it)
        }
        cancelEdit()
    }

    fun updateText(message: String) {
        post.value?.let {
            val text = message.trim()
            if (text.isEmpty() || it.text == text) {
                return
            }
            post.value = it.copy(text = text)
        }
    }

    fun edit(item: Post) {
        post.value = item
        sourceMessage.value = item.text
    }

    fun cancelEdit() {
        post.value = Post.getEmpty()
        sourceMessage.value = null
    }
}