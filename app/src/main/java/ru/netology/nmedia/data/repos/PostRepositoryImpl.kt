package ru.netology.nmedia.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.entities.Post
import java.text.SimpleDateFormat
import java.util.*

class PostRepositoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий",
            authorAvatar = "@sample/posts_avatars",
            published = "21 мая в 18:36",
            text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor." +
                    "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
                    "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
                    "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, " +
                    "imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. " +
                    "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, " +
                    "porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, " +
                    "feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet.",
            isLiked = false,
            likesCount = 52,
            commentsCount = 6,
            repostsCount = 998,
            viewsCount = 7312
        ),
        Post(
            id = 2,
            author = "Нетология. Университет интернет-профессий",
            authorAvatar = "@sample/posts_avatars",
            published = "21 мая в 18:36",
            text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor." +
                    "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
                    "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
                    "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, " +
                    "imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. " +
                    "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, " +
                    "porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, " +
                    "feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet.",
            isLiked = false,
            likesCount = 43,
            commentsCount = 8,
            repostsCount = 1200,
            viewsCount = 5679
        ),
        Post(
            id = 3,
            author = "Нетология. Университет интернет-профессий",
            authorAvatar = "@sample/posts_avatars",
            published = "21 мая в 18:36",
            text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor." +
                    "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
                    "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
                    "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, " +
                    "imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. " +
                    "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, " +
                    "porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, " +
                    "feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet.",
            isLiked = false,
            likesCount = 12,
            commentsCount = 57,
            repostsCount = 355,
            viewsCount = 5464
        ),
    )

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

        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(repostsCount = it.repostsCount + 1)
            }
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { item -> item.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        posts = if (post.id != 0L) {
            posts.map { item ->  if (item.id == post.id) { post } else { item }}
        } else {
            val item = post.copy(id = posts.maxOf { i -> i.id }.inc(), published = getTime())
            posts.toMutableList()
                .apply { add(item) }
        }
        data.value = posts
    }

    private fun getTime(): String =
        SimpleDateFormat.getDateInstance().format(Date(System.currentTimeMillis()))
}