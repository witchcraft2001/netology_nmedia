package ru.netology.nmedia.ui.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.data.entities.Post

class EditPostViewModel: ViewModel() {
    val post = MutableLiveData(Post.getEmpty())
    val sourceMessage = MutableLiveData<String>(null)

    fun setPost(item: Post) {
        post.value = item
        if (item.id != 0L) {
            sourceMessage.value = item.text
        }
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

    fun updateVideoText(message: String) {
        post.value?.let {
            val video = message.trim()
            if (video.isEmpty() || it.video == video) {
                return
            }
            post.value = it.copy(video = video)
        }
    }
}