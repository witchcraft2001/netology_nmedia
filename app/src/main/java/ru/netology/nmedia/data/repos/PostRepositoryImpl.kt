package ru.netology.nmedia.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.entities.Post

class PostRepositoryImpl: PostRepository {
    private var post = Post(
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
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(isLiked = !post.isLiked, likesCount = if (post.isLiked) post.likesCount - 1 else post.likesCount + 1)
        data.value = post
    }

    override fun share() {
        post = post.copy(repostsCount = post.repostsCount + 1)
        data.value = post
    }
}