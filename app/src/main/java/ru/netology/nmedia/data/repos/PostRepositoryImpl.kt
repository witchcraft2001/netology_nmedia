package ru.netology.nmedia.data.repos

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.netology.nmedia.data.entities.Post
import java.text.SimpleDateFormat
import java.util.*

class PostRepositoryImpl(
    context: Context
) : PostRepository {

    companion object {
        private const val PREF_KEY_POSTS = "posts"
        private const val PREF_POST_REPO = "post_repo"
    }

    private var posts = emptyList<Post>()
    private val prefs = context.getSharedPreferences(PREF_POST_REPO, Context.MODE_PRIVATE)

    init {
        prefs.getString(PREF_KEY_POSTS, null)?.let {
            posts = Json.decodeFromString(it)
        }
    }

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(
                    isLiked = !it.isLiked,
                    likesCount = if (it.isLiked) it.likesCount - 1 else it.likesCount + 1
                )
            }
        }
        syncRepo()
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(repostsCount = it.repostsCount + 1, isShared = true)
            }
        }
        syncRepo()
    }

    override fun removeById(id: Long) {
        posts = posts.filter { item -> item.id != id }
        syncRepo()
    }

    override fun save(post: Post) {
        posts = if (post.id != 0L) {
            posts.map { item ->
                if (item.id == post.id) {
                    post
                } else {
                    item
                }
            }
        } else {
            val item = post.copy(id = (posts.maxOfOrNull { i -> i.id } ?: 0).inc(), published = getTime())
            posts.toMutableList()
                .apply { add(item) }
        }
        syncRepo()
    }

    private fun getTime(): String =
        SimpleDateFormat.getDateInstance().format(Date(System.currentTimeMillis()))

    private fun syncRepo() {
        val json = Json.encodeToString(posts)
        prefs.edit().putString(PREF_KEY_POSTS, json).apply()
        data.value = posts
    }
}